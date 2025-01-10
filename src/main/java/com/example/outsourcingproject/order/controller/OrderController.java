package com.example.outsourcingproject.order.controller;

import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;
import com.example.outsourcingproject.order.service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    @PatchMapping
    public ResponseEntity<UpdateOrderResponseDto> updateOrderStatus(
        @RequestBody UpdateOrderRequestDto requestDto
    ) {
        UpdateOrderResponseDto responseDto = orderService.updateOrderStatus(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
