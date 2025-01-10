package com.example.outsourcingproject.store.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MenuDto {
    private final Long id;
    private final String menuName;
    private final Integer menuPrice;
    private final String menuInfo;

    public MenuDto(
        Long id,
        String menuName,
        Integer menuPrice,
        String menuInfo)
    {
        this.id = id;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuInfo = menuInfo;
    }
}
