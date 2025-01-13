package com.example.outsourcingproject.category.controller;

import com.example.outsourcingproject.category.dto.request.CreateMenuCategoryRequestDto;
import com.example.outsourcingproject.category.dto.response.CreateMenuCategoryResponseDto;
import com.example.outsourcingproject.category.service.MenuCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu-categories")
@RequiredArgsConstructor
public class MenuCategoryController {

    private final MenuCategoryService menuCategoryService;

    @PostMapping
    public ResponseEntity<CreateMenuCategoryResponseDto> createMenuCategory(
        @RequestBody CreateMenuCategoryRequestDto requestDto
    ) {
        CreateMenuCategoryResponseDto responseDto = menuCategoryService.createMenuCategory(
            requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}