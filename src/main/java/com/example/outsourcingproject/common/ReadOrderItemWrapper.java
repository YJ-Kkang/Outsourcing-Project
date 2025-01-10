package com.example.outsourcingproject.common;


import com.example.outsourcingproject.order.OrderStatus;
import com.example.outsourcingproject.orderitem.dto.response.OrderItemResponseDto;
import java.util.List;
import lombok.Getter;

@Getter
public class ReadOrderItemWrapper {

    private final List<OrderItemResponseDto> orderDetails;
    private final Integer totalAmountSum;
    private final Integer totalPriceSum;
    private final Long orderId;
    private final OrderStatus orderStatus;

    public ReadOrderItemWrapper(
        List<OrderItemResponseDto> orderDetails,
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
