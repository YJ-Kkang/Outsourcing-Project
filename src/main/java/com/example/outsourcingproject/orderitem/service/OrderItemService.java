package com.example.outsourcingproject.orderitem.service;

import com.example.outsourcingproject.common.CreateOrderItemWrapper;
import com.example.outsourcingproject.common.ReadOrderItemWrapper;
import com.example.outsourcingproject.orderitem.dto.request.CreateOrderItemRequestDto;
import java.util.List;

public interface OrderItemService {

    CreateOrderItemWrapper createOrderItem(
        Long storeId,
        List<CreateOrderItemRequestDto> requestDtoList
    );

    ReadOrderItemWrapper readAllOrderItemsByOrderId(Long orderId);
}