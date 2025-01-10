package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.auth.repository.OwnerAuthRepository;
import com.example.outsourcingproject.entity.Menu;
import com.example.outsourcingproject.entity.Owner;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.exception.CustomException;
import com.example.outsourcingproject.exception.ErrorCode;
import com.example.outsourcingproject.menu.repository.MenuRepository;
import com.example.outsourcingproject.store.dto.MenuDto;
import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreNameSearchResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreResponseDto;
import com.example.outsourcingproject.store.repository.StoreRepository;
import com.example.outsourcingproject.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final OwnerAuthRepository ownerAuthRepository;
    private final JwtUtil jwtUtil;
    private final MenuRepository menuRepository;

    @Transactional
    @Override
    public CreateStoreResponseDto createStore(
        CreateStoreRequestDto requestDto,
        String token
    ) {
        String ownerEmail = jwtUtil.extractOwnerEmail(token);

        Owner foundOwner = ownerAuthRepository.findByEmail(ownerEmail)
            .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED)); // todo

        Long storeCount = storeRepository.countByOwnerId(foundOwner.getId());

        if (storeCount > 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } // todo 사장님의 가게 수가 최대 3개이므로 4개째부터 예외 발생

        Store storeToSave = new Store(
            foundOwner.getId(),
            requestDto.getStoreName(),
            requestDto.getStoreAddress(),
            requestDto.getStoreTelephone(),
            requestDto.getMinimumPurchase(),
            requestDto.getOpensAt(),
            requestDto.getClosesAt()
        );

        Store savedStore = storeRepository.save(storeToSave);

        return new CreateStoreResponseDto(savedStore);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StoreNameSearchResponseDto> readAllStoresByStoreName(String storeName) {

        List<Store> storeList = new ArrayList<>();

        storeList = storeRepository.findByStoreNameContainingAndIsDeletedFalse(storeName);

        List<StoreNameSearchResponseDto> responseDtoList = new ArrayList<>();

        for (Store foundStore : storeList) {
            StoreNameSearchResponseDto responseDto = new StoreNameSearchResponseDto(foundStore);

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }


    // 가게 및 해당 가게 메뉴 조회
    @Override
    public StoreResponseDto findByStoreId(Long storeId) {

        Store store = storeRepository.findById(storeId)
            .orElseThrow(() -> new EntityNotFoundException("가게를 찾을 수 없습니다.")); // todo 예외치리

        //1. 메뉴리스트 가져오기 (객체메뉴 생성
        List<Menu> menuList = menuRepository.findAllByStoreId(store.getId());
        //2. 메뉴dto가 들어 있는 리스트 선언
        List<MenuDto> menuDtoList = new ArrayList<>();
        //3. 변환하기 (타입이 다르므로 Menu를 menuList에서 ‘하나씩’ 꺼내서 MenuDto 타입으로 변환한 다음에, menuDtoList에 넣기 ‘반복’

        for (Menu menu : menuList) {
            MenuDto menuDto = new MenuDto(
                menu.getId(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                menu.getMenuInfo());
            menuDtoList.add(menuDto);
        }

        return new StoreResponseDto(
            store.getStoreName(),
            store.getStoreAddress(),
            store.getStoreTelephone(),
            store.getMinimumPurchase(),
            store.getOpensAt(),
            store.getClosesAt(),
            menuDtoList
        );
    }

    // 가게 수정
    @Override
    public StoreResponseDto updateStore(String storeName, String storeAddress,
        String storeTelephone, Integer minimumPurchase, LocalTime opensAt, LocalTime closesAt) {
        return null;
    }

    // 가게 폐업
    @Override
    public void deleteStore(Long storeId) {

    }

}
