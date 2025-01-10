package com.example.outsourcingproject.orderitem.service;

import com.example.outsourcingproject.common.OrderItemWrapper;
import com.example.outsourcingproject.orderitem.dto.request.CreateOrderItemRequestDto;
import java.util.List;

public interface OrderItemService {

    OrderItemWrapper createOrderItem(
        Long storeId,
        List<CreateOrderItemRequestDto> requestDtoList
    );
}