package com.example.outsourcingproject.category.controller;

import com.example.outsourcingproject.category.dto.request.CreateStoreCategoryRequestDto;
import com.example.outsourcingproject.category.dto.response.CreateStoreCategoryResponseDto;
import com.example.outsourcingproject.category.service.StoreCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store-categories")
@RequiredArgsConstructor
public class StoreCategoryController {

    private final StoreCategoryService storeCategoryService;

    @PostMapping
    public ResponseEntity<CreateStoreCategoryResponseDto> createStoreCategory(
        @RequestBody CreateStoreCategoryRequestDto requestDto
    ) {
        CreateStoreCategoryResponseDto responseDto = storeCategoryService.createCategory(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}