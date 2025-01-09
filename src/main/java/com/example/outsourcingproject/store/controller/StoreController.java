package com.example.outsourcingproject.store.controller;


import com.example.outsourcingproject.entity.StoreEntity;
import com.example.outsourcingproject.store.dto.request.CreateStoreRequestDto;
import com.example.outsourcingproject.store.dto.response.CreateStoreResponseDto;
import com.example.outsourcingproject.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }


    @PostMapping
    public ResponseEntity<CreateStoreResponseDto> createStore(
        @RequestBody CreateStoreRequestDto requestDto) {

        CreateStoreResponseDto store = storeService.createStore(requestDto);

        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

}

