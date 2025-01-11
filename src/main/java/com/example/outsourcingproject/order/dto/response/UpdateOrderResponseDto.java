package com.example.outsourcingproject.order.dto.response;

import com.example.outsourcingproject.order.OrderState;
import lombok.Getter;

@Getter
public class UpdateOrderResponseDto {

    private final OrderState updatedOrderState;

    public UpdateOrderResponseDto(
        OrderState updatedOrderState) {
        this.updatedOrderState = updatedOrderState;
    }
}