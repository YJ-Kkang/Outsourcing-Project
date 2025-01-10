package com.example.outsourcingproject.menu.controller;

import com.example.outsourcingproject.menu.dto.request.CreateMenuRequestDto;
import com.example.outsourcingproject.menu.dto.request.UpdateMenuRequestDto;
import com.example.outsourcingproject.menu.dto.response.CreateMenuResponseDto;
import com.example.outsourcingproject.menu.dto.response.UpdateMenuResponseDto;
import com.example.outsourcingproject.menu.service.MenuServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores/{storeId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuServiceImpl menuService;

    @PostMapping
    public ResponseEntity<CreateMenuResponseDto> createMenu(
        @PathVariable("storeId") Long storeId,
        @RequestBody CreateMenuRequestDto requestDto
    ) {
        CreateMenuResponseDto responseDto = menuService.createMenu(
            storeId,
            requestDto
        );

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{menuId}")
    public ResponseEntity<UpdateMenuResponseDto> updateMenu(
        @PathVariable("storeId") Long storeId,
        @PathVariable("menuId") Long menuId,
        @RequestBody UpdateMenuRequestDto requestDto
    ) {
        UpdateMenuResponseDto responseDto = menuService.updateMenu(
            storeId,
            menuId,
            requestDto
        );
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
