package com.example.outsourcingproject.orderitem.dto.response;

import lombok.Getter;

@Getter
public class CreateOrderItemResponseDto {

    private final Long id;
    private final Long menuId;
    private final Integer eachAmount;
    private final Integer eachPrice;
    private final Integer totalPrice;

    public CreateOrderItemResponseDto(
        Long id,
        Long menuId,
        Integer eachAmount,
        Integer eachPrice,
        Integer totalPrice
    ) {
        this.id = id;
        this.menuId = menuId;
        this.eachAmount = eachAmount;
        this.eachPrice = eachPrice;
        this.totalPrice = totalPrice;
    }
}