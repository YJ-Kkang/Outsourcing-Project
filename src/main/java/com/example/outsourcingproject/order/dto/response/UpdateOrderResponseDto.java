package com.example.outsourcingproject.order.dto.response;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.order.OrderState;
import lombok.Getter;

@Getter
public class UpdateOrderResponseDto {

    private final OrderState updatedOrderState;
    private final Long storeId;

    public UpdateOrderResponseDto(Order order) {
        this.storeId = order.getStore().getId();
        this.updatedOrderState = order.getOrderState();
    }
}