package com.example.outsourcingproject.store.service;

import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;

public interface StoreService {

   CreateStoreResponseDto createStore(CreateStoreRequestDto requestDto);

//   List<StoreResponseDto> findAllStores();
//
//   List<StoreResponseDto> findStoresBySearch(String search);
}
