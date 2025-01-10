package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.auth.repository.OwnerAuthRepository;
import com.example.outsourcingproject.entity.Owner;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.exception.CustomException;
import com.example.outsourcingproject.exception.ErrorCode;
import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreResponseDto;
import com.example.outsourcingproject.store.repository.StoreRepository;
import com.example.outsourcingproject.utils.JwtUtil;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final OwnerAuthRepository ownerAuthRepository;
    private final JwtUtil jwtUtil;

    @Override
    public CreateStoreResponseDto createStore(
        CreateStoreRequestDto requestDto,
        String token
    ) {
        String storeName = requestDto.getStoreName();
        String storeAddress = requestDto.getStoreAddress();
        String storeTelephone = requestDto.getStoreTelephone();
        Integer minimumPurchase = requestDto.getMinimumPurchase();
        LocalTime opensAt = requestDto.getOpensAt();
        LocalTime closesAt = requestDto.getClosesAt();

        // jwt 토큰에 저장된 사장님 이메일 추출
        String ownerEmail = jwtUtil.extractCustomerEmail(token);

        // 사장님 이메일로 사장님 아이디 추출
        Owner owner = ownerAuthRepository.findByEmail(ownerEmail)
            .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));
        Long ownerId = owner.getId();

        // StoreEntity 생성 (가게 정보를 엔티티로 변환)
        Store store = new Store(
            ownerId, storeName, storeAddress, storeTelephone,
            minimumPurchase,opensAt, closesAt
        );

        // 데이터베이스에 가게 저장
        Store savedStore = storeRepository.save(store);

        return new CreateStoreResponseDto(
            savedStore.getId(),
            savedStore.getStoreName(),
            savedStore.getStoreAddress(),
            savedStore.getStoreTelephone(),
            savedStore.getMinimumPurchase(),
            savedStore.getOpensAt(),
            savedStore.getClosesAt());
    }

    // 가게 다건 조회
    @Override
    public List<Store> findByStoreNameContaining(String storeName) {
        List<Store> storeEntityList = storeRepository.findByStoreNameContaining(storeName); // %LIKE%

//        return storeEntityList.stream()
//            .map(StoreResponseDto::new)
//            .collect(Collectors.toList());
        return storeEntityList;

    }

    // 가게 단건 조회
    @Override
    public StoreResponseDto findByStoreId(Long storeId) {
        return null;
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
