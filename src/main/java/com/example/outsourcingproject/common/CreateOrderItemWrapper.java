package com.example.outsourcingproject.common;

import com.example.outsourcingproject.order.OrderStatus;
import com.example.outsourcingproject.orderitem.dto.response.CreateOrderItemResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateOrderItemWrapper {

    private final List<CreateOrderItemResponseDto> orderDetails;
    private final Integer totalAmountSum;
    private final Integer totalPriceSum;
    private final Long orderId;
    private final OrderStatus orderStatus;

    public CreateOrderItemWrapper(
        List<CreateOrderItemResponseDto> orderDetails,
        Integer totalAmountSum,
        Integer totalPriceSum,
        Long orderId,
        OrderStatus orderStatus
    ) {
        this.orderDetails = orderDetails;
        this.totalAmountSum = totalAmountSum;
        this.totalPriceSum = totalPriceSum;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
