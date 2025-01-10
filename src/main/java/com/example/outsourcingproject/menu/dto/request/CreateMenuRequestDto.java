package com.example.outsourcingproject.menu.dto.request;

import lombok.Getter;

@Getter
public class CreateMenuRequestDto {

    private final String menuName;
    private final Integer menuPrice;
    private final String menuInfo;

    public CreateMenuRequestDto(
        String menuName,
        Integer menuPrice,
        String menuInfo
    ) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuInfo = menuInfo;
    }
}