package com.example.outsourcingproject.category.controller;

import com.example.outsourcingproject.category.dto.request.CreateCategoryRequestDto;
import com.example.outsourcingproject.category.dto.response.CreateCategoryResponseDto;
import com.example.outsourcingproject.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CreateCategoryResponseDto> createCategory(
        @RequestBody CreateCategoryRequestDto requestDto
    ) {
        CreateCategoryResponseDto responseDto = categoryService.createCategory(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}