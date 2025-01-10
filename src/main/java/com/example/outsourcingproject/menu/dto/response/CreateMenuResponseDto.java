package com.example.outsourcingproject.menu.dto.response;

import lombok.Getter;

@Getter
public class CreateMenuResponseDto {

    private final Long id;
    private final String menuName;
    private final Integer menuPrice;
    private final String menuInfo;


    public CreateMenuResponseDto(
        Long id,
        String menuName,
        Integer menuPrice,
        String menuInfo
    ) {
        this.id = id;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuInfo = menuInfo;
    }
}
