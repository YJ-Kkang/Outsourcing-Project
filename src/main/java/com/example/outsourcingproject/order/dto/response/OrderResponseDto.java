package com.example.outsourcingproject.order.dto.response;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private final Long storeId;
    private final Long orderId;
    private final Integer totalAmountSum;
    private final Integer totalPriceSum;
    private final OrderStatus orderStatus;

    public OrderResponseDto(Order order) {
        this.storeId = order.getStore().getId();
        this.orderId = order.getId();
        this.totalAmountSum = order.getTotalAmountSum();
        this.totalPriceSum = order.getTotalPriceSum();
        this.orderStatus = order.getOrderStatus();
    }
}