package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.entity.StoreEntity;
import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public CreateStoreResponseDto createStore(CreateStoreRequestDto requestDto) {

        String storeName = requestDto.getStoreName();
        String storeAddress = requestDto.getStoreAddress();
        String storeTelephone = requestDto.getStoreTelephone();
        BigInteger minimumPurchase = requestDto.getMinimumPurchase();
        LocalTime opensAt = requestDto.getOpensAt();
        LocalTime closesAt = requestDto.getClosesAt();

        // StoreEntity 생성 (가게 정보를 엔티티로 변환)
        StoreEntity store = new StoreEntity();

        // todo 세터라서 고쳐야함
        store.setStoreName(storeName);
        store.setStoreAddress(storeAddress);
        store.setStoreTelephone(storeTelephone);
        store.setMinimumPurchase(minimumPurchase);
        store.setOpensAt(opensAt);
        store.setClosesAt(closesAt);

        StoreEntity savedStore = storeRepository.save(store);
        // 데이터베이스에 가게 저장
        return new CreateStoreResponseDto(
            savedStore.getId(),
            savedStore.getStoreName(),
            savedStore.getStoreAddress(),
            savedStore.getStoreTelephone(),
            savedStore.getMinimumPurchase(),
            savedStore.getOpensAt(),
            savedStore.getClosesAt());
    }
}
