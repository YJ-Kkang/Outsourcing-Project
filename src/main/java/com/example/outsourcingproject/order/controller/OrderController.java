package com.example.outsourcingproject.order.controller;

import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.OrderResponseDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;
import com.example.outsourcingproject.order.service.OrderServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;

    // todo : 사장님 권한 추가해야 함
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> readAllOrdersAllStores() {

        List<OrderResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = orderService.readAllOrdersFromStores();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    // todo : 사장님 권한 추가해야 함
    @GetMapping("/{storeId}")
    public ResponseEntity<List<OrderResponseDto>> readAllOrdersByStoreId(
        @PathVariable("storeId") Long storeId
    ) {
        List<OrderResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = orderService.readAllOrdersByStoreId(storeId);

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UpdateOrderResponseDto> updateOrderStatus(
        @RequestBody UpdateOrderRequestDto requestDto
    ) {
        UpdateOrderResponseDto responseDto = orderService.updateOrderStatus(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}