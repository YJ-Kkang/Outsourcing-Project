package com.example.outsourcingproject.order.service;

import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;

public interface OrderService {

    UpdateOrderResponseDto updateOrderStatus(UpdateOrderRequestDto requestDto);

}