package com.example.outsourcingproject.menu.service;

import com.example.outsourcingproject.menu.dto.request.CreateMenuRequestDto;
import com.example.outsourcingproject.menu.dto.request.UpdateMenuRequestDto;
import com.example.outsourcingproject.menu.dto.response.CreateMenuResponseDto;
import com.example.outsourcingproject.menu.dto.response.UpdateMenuResponseDto;

public interface MenuService {

    CreateMenuResponseDto createMenu(
        Long storeId,
        CreateMenuRequestDto requestDto
    );

    UpdateMenuResponseDto updateMenu(
        Long storeId,
        Long menuId,
        UpdateMenuRequestDto requestDto
    );


}
