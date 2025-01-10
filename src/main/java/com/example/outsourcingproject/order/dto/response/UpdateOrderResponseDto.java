package com.example.outsourcingproject.order.dto.response;

import com.example.outsourcingproject.order.OrderStatus;
import lombok.Getter;

@Getter
public class UpdateOrderResponseDto {

    private final OrderStatus updatedOrderStatus;

    public UpdateOrderResponseDto(
        OrderStatus updatedOrderStatus) {
        this.updatedOrderStatus = updatedOrderStatus;
    }
}