package com.example.outsourcingproject.order.service;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.exception.notfound.OrderNotFoundException;
import com.example.outsourcingproject.exception.notfound.StoreNotFoundException;
import com.example.outsourcingproject.order.OrderState;
import com.example.outsourcingproject.order.dto.request.UpdateOrderRequestDto;
import com.example.outsourcingproject.order.dto.response.OrderResponseDto;
import com.example.outsourcingproject.order.dto.response.UpdateOrderResponseDto;
import com.example.outsourcingproject.order.repository.OrderRepository;
import com.example.outsourcingproject.store.repository.StoreRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponseDto> readAllOrdersFromStores() {
        List<Order> orderList = new ArrayList<>();

        orderList = orderRepository.findAll();

        List<OrderResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = orderList.stream()
            .map(OrderResponseDto::new)
            .toList();

        return responseDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderResponseDto> readAllOrdersByStoreId(Long storeId) {

        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(StoreNotFoundException::new);

        List<Order> orderList = orderRepository.findAllByStoreId(foundStore.getId());

        List<OrderResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = orderList.stream()
            .map(OrderResponseDto::new)
            .toList();

        return responseDtoList;
    }

    @Transactional
    @Override
    public UpdateOrderResponseDto updateOrderStatus(UpdateOrderRequestDto requestDto) {
        Order foundOrder = orderRepository.findByIdAndOrderStateNot(
            requestDto.getId(),
            OrderState.DELIVERED
        ).orElseThrow(OrderNotFoundException::new);

        OrderState nextStatus = OrderState.of(requestDto.getOrderState());

        foundOrder.updateOrderStatus(nextStatus);

        return new UpdateOrderResponseDto(foundOrder.getOrderState());
    }
}