package com.example.outsourcingproject.order.service;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.order.OrderStatus;
import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.OrderResponseDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;
import com.example.outsourcingproject.order.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public UpdateOrderResponseDto updateOrderStatus(UpdateOrderRequestDto requestDto) {
        Order foundOrder = orderRepository.findByIdAndOrderStatusNot(
            requestDto.getId(),
            OrderStatus.DELIVERED
        ).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        ); // todo 배달 완료 제외한 주문 조회 및 없을 시 예외 처리

        OrderStatus nextStatus = OrderStatus.of(requestDto.getOrderStatus());

        foundOrder.updateOrderStatus(nextStatus);

        return new UpdateOrderResponseDto(foundOrder.getOrderStatus());
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponseDto> readAllOrdersFromStores() {
        List<Order> orderList = new ArrayList<>();

        orderList = orderRepository.findAll();

        List<OrderResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = orderList.stream().
            map(order -> new OrderResponseDto(
                    order.getStore().getId(),
                    order.getId(),
                    order.getTotalAmountSum(),
                    order.getTotalPriceSum(),
                    order.getOrderStatus()
                )
            ).toList();

        return responseDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public OrderResponseDto readAllOrdersByStoreId(Long storeId) {
        return null;
    }
}