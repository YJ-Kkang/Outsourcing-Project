package com.example.outsourcingproject.orderitem.controller;

import com.example.outsourcingproject.common.OrderItemWrapper;
import com.example.outsourcingproject.orderitem.dto.request.CreateOrderItemRequestDto;
import com.example.outsourcingproject.orderitem.service.OrderItemServiceImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderItemController {

    private final OrderItemServiceImpl orderItemService;

    public OrderItemController(OrderItemServiceImpl orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/stores/{storeId}/orders")
    public ResponseEntity<OrderItemWrapper> createOrderItem(
        @PathVariable Long storeId,
        @RequestBody List<CreateOrderItemRequestDto> requestDtoList
    ) {
        OrderItemWrapper responseDtoWrapper = orderItemService.createOrderItem(
            storeId,
            requestDtoList
        );

        return new ResponseEntity<>(responseDtoWrapper, HttpStatus.CREATED);
    }
}