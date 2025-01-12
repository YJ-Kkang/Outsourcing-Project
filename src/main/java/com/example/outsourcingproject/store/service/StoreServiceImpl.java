package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.auth.repository.OwnerAuthRepository;
import com.example.outsourcingproject.category.repository.CategoryRepository;
import com.example.outsourcingproject.entity.Category;
import com.example.outsourcingproject.entity.Menu;
import com.example.outsourcingproject.entity.Owner;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.exception.CustomException;
import com.example.outsourcingproject.exception.ErrorCode;
import com.example.outsourcingproject.exception.badrequest.CategoryInvalidCountException;
import com.example.outsourcingproject.exception.badrequest.StoreInvalidCountExcessException;
import com.example.outsourcingproject.exception.notfound.OwnerNotFoundException;
import com.example.outsourcingproject.exception.notfound.StoreNotFoundException;
import com.example.outsourcingproject.menu.repository.MenuRepository;
import com.example.outsourcingproject.store.dto.MenuDto;
import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.request.UpdateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreNameSearchResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreResponseDto;
import com.example.outsourcingproject.store.dto.response.UpdateStoreResponseDto;
import com.example.outsourcingproject.store.repository.StoreRepository;
import com.example.outsourcingproject.utils.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CreateStoreResponseDto createStore(
        CreateStoreRequestDto requestDto,
        String token
    ) {
        String ownerEmail = jwtUtil.extractOwnerEmail(token);

        Owner foundOwner = ownerAuthRepository.findByEmail(ownerEmail)
            .orElseThrow(OwnerNotFoundException::new);

        Long storeCount = storeRepository.countByOwnerIdAndIsDeleted(foundOwner.getId(), 0);

        if (storeCount >= 3) {
            throw new StoreInvalidCountExcessException();
        }

        List<String> categoryNameList = new ArrayList<>();

        categoryNameList = requestDto.getCategoryNameList();

        if (categoryNameList.size() != 2) {
            throw new CategoryInvalidCountException();
        }

        List<Category> categoryList = new ArrayList<>();

        categoryList = categoryRepository.findAllByNameIn(
            categoryNameList,
            Sort.unsorted()
        );

        Store storeToSave = new Store(
            foundOwner.getId(),
            requestDto.getStoreName(),
            requestDto.getStoreAddress(),
            requestDto.getStoreTelephone(),
            requestDto.getMinimumPurchase(),
            requestDto.getOpensAt(),
            requestDto.getClosesAt(),
            categoryList.get(0),
            categoryList.get(1)
        );

        Store savedStore = storeRepository.save(storeToSave);

        return new CreateStoreResponseDto(savedStore);
    }

    @Transactional(readOnly = true)
    @Override
    public List<StoreNameSearchResponseDto> readAllStoresByStoreName(String storeName) {

        List<Store> storeList = new ArrayList<>();

        storeList = storeRepository.findByStoreNameContainingAndIsDeleted(
            storeName,
            0
        );

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

        menuList = menuRepository.findAllByStoreIdAndIsDeleted(
            foundStore.getId(),
            0
        );

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

    @Override
    public UpdateStoreResponseDto updateStore(
        Long storeId,
        UpdateStoreRequestDto requestDto
    ) {
        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(StoreNotFoundException::new);

        foundStore.update(
            requestDto.getStoreName(),
            requestDto.getStoreAddress(),
            requestDto.getStoreTelephone(),
            requestDto.getMinimumPurchase(),
            requestDto.getOpensAt(),
            requestDto.getClosesAt()
        );

        storeRepository.save(foundStore);

        return new UpdateStoreResponseDto(foundStore);
    }

    @Override
    @Transactional
    public void deleteStore(Long storeId, String token) {

        /**
         * 토큰으로 권한 인증을 받은 사장님과 경로를 통해 값을 받아서 그 상점의 사장님과 같은지 검증로직
         */
        String ownerEmail = jwtUtil.extractOwnerEmail(token);

        Owner foundOwner = ownerAuthRepository.findByEmail(ownerEmail)
            .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(StoreNotFoundException::new);

        boolean isIdMismatching = !(foundOwner.getId().equals(foundStore.getOwnerId()));

        if (isIdMismatching) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        foundStore.markAsDeleted();
    }
}