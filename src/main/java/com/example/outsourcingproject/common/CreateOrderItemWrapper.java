package com.example.outsourcingproject.common;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.order.OrderState;
import com.example.outsourcingproject.orderitem.dto.response.CreateOrderItemResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateOrderItemWrapper {

    private final List<CreateOrderItemResponseDto> orderDetails;
    private final Integer totalAmountSum;
    private final Integer totalPriceSum;
    private final Long orderId;
    private final OrderState orderState;

    public CreateOrderItemWrapper(
        List<CreateOrderItemResponseDto> orderDetails,
        Integer totalAmountSum,
        Integer totalPriceSum,
        Order order
    ) {
        this.orderDetails = orderDetails;
        this.totalAmountSum = totalAmountSum;
        this.totalPriceSum = totalPriceSum;
        this.orderId = order.getId();
        this.orderState = order.getOrderState();
    }
}
