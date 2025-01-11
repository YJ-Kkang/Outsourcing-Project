package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreNameSearchResponseDto;
import com.example.outsourcingproject.store.dto.response.StoreResponseDto;
import java.time.LocalTime;
import java.util.List;

public interface StoreService {

    CreateStoreResponseDto createStore(
        CreateStoreRequestDto requestDto,
        String token
    );

    List<StoreNameSearchResponseDto> readAllStoresByStoreName(String storeName);

    StoreResponseDto findStoreByStoreId(Long storeId);

    StoreResponseDto updateStore(
        String storeName,
        String storeAddress,
        String storeTelephone,
        Integer minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt
    );

    void deleteStore(Long storeId);
}
