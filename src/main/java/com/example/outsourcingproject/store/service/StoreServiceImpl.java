package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.auth.repository.OwnerAuthRepository;
import com.example.outsourcingproject.entity.Menu;
import com.example.outsourcingproject.entity.Owner;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.exception.notfound.OwnerNotFoundException;
import com.example.outsourcingproject.exception.notfound.StoreNotFoundException;
import com.example.outsourcingproject.menu.repository.MenuRepository;
import com.example.outsourcingproject.store.dto.MenuDto;
import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreNameSearchResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreResponseDto;
import com.example.outsourcingproject.store.repository.StoreRepository;
import com.example.outsourcingproject.utils.JwtUtil;
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
            .orElseThrow(OwnerNotFoundException::new);

        Long storeCount = storeRepository.countByOwnerId(foundOwner.getId());

        if (storeCount >= 3) {
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

        storeList = storeRepository.findByStoreNameContainingAndIsDeleted(storeName, 0);

        List<StoreNameSearchResponseDto> responseDtoList = new ArrayList<>();

        for (Store foundStore : storeList) {
            StoreNameSearchResponseDto responseDto = new StoreNameSearchResponseDto(foundStore);

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public StoreResponseDto findStoreByStoreId(Long storeId) {

        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(StoreNotFoundException::new);

        List<Menu> menuList = new ArrayList<>();

        menuList = menuRepository.findAllByStoreIdAndIsDeleted(foundStore.getId(), 0);

        List<MenuDto> menuDtoList = new ArrayList<>();

        for (Menu foundMenu : menuList) {
            MenuDto menuDto = new MenuDto(foundMenu);
            menuDtoList.add(menuDto);
        }

        return new StoreResponseDto(
            foundStore,
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
