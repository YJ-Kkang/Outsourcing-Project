package com.example.outsourcingproject.order.dto.response;

import com.example.outsourcingproject.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private final Long storeId;
    private final Long orderId;
    private final Integer totalAmountSum;
    private final Integer totalPriceSum;
    private final OrderStatus orderStatus;

    public OrderResponseDto(
        Long storeId,
        Long orderId,
        Integer totalAmountSum,
        Integer totalPriceSum,
        OrderStatus orderStatus
    ) {
        this.storeId = storeId;
        this.orderId = orderId;
        this.totalAmountSum = totalAmountSum;
        this.totalPriceSum = totalPriceSum;
        this.orderStatus = orderStatus;
    }
}
