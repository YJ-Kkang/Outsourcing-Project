package com.example.outsourcingproject.orderitem.service;

import com.example.outsourcingproject.common.CreateOrderItemWrapper;
import com.example.outsourcingproject.common.ReadOrderItemWrapper;
import com.example.outsourcingproject.entity.Menu;
import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.entity.OrderItem;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.menu.repository.MenuRepository;
import com.example.outsourcingproject.order.OrderStatus;
import com.example.outsourcingproject.order.repository.OrderRepository;
import com.example.outsourcingproject.orderitem.dto.request.CreateOrderItemRequestDto;
import com.example.outsourcingproject.orderitem.dto.response.CreateOrderItemResponseDto;
import com.example.outsourcingproject.orderitem.dto.response.OrderItemResponseDto;
import com.example.outsourcingproject.orderitem.repository.OrderItemRepository;
import com.example.outsourcingproject.store.repository.StoreRepository;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    @Transactional
    @Override
    public CreateOrderItemWrapper createOrderItem(
        Long storeId,
        List<CreateOrderItemRequestDto> requestDtoList
    ) {

        Store foundStore = storeRepository.findById(storeId)
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND
                )
            ); // todo 가게 없을 시 예외 처리 -> '가게가 폐업 상태일 때 예외 처리 필요'

        LocalTime timeToOrder = LocalTime.now();
        boolean isBeforeOpensAt = timeToOrder.isBefore(foundStore.getOpensAt());
        boolean isAfterClosesAt = timeToOrder.isAfter(foundStore.getClosesAt());

        if (isBeforeOpensAt || isAfterClosesAt) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST
            );
        } // todo 가게 오픈 시간 전이나 종료 시간 후 주문 시 예외 처리

        Order orderToSave = new Order(
            OrderStatus.PENDING,
            foundStore
        );

        Order savedOrder = orderRepository.save(orderToSave);

        List<CreateOrderItemResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = requestDtoList.stream()
            .map(requestDto -> {

                    Menu foundMenu = menuRepository.findById(requestDto.getMenuId())
                        .orElseThrow(
                            () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND
                            )
                        ); // todo 메뉴가 없을 시 예외 처리

                    OrderItem orderItemToSave = new OrderItem(
                        savedOrder,
                        foundMenu,
                        requestDto.getEachAmount()
                    );

                    OrderItem savedOrderItem = orderItemRepository.save(orderItemToSave);

                    return new CreateOrderItemResponseDto(
                        savedOrderItem.getId(),
                        foundMenu.getId(),
                        savedOrderItem.getEachAmount(),
                        foundMenu.getMenuPrice(),
                        savedOrderItem.getTotalPrice()
                    );
                }
            ).toList();

        Integer totalPriceSum = responseDtoList.stream()
            .mapToInt(CreateOrderItemResponseDto::getTotalPrice)
            .sum();

        boolean isBelowMinimumPurchase = totalPriceSum < foundStore.getMinimumPurchase();

        if (isBelowMinimumPurchase) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } // todo 최소 주문 금액보다 적으면 예외 처리

        Integer totalAmountSum = responseDtoList.stream()
            .mapToInt(CreateOrderItemResponseDto::getEachAmount)
            .sum();

        savedOrder.updateTotals(totalAmountSum, totalPriceSum);
        orderRepository.save(savedOrder);

        return new CreateOrderItemWrapper(
            responseDtoList,
            totalAmountSum,
            totalPriceSum,
            savedOrder.getId(),
            savedOrder.getOrderStatus()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public ReadOrderItemWrapper readAllOrderItemsByOrderId(Long orderId) {

        Order foundOrder = orderRepository.findById(orderId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 주문 식별자로 조회

        List<OrderItem> orderItemList = new ArrayList<>();

        orderItemList = orderItemRepository.findAllByOrderId(foundOrder.getId());

        List<OrderItemResponseDto> responseDtoList = new ArrayList<>();

        responseDtoList = orderItemList.stream()
            .map(item -> new OrderItemResponseDto(
                    item.getId(),
                    item.getEachAmount(),
                    item.getTotalPrice()
                )
            ).toList();

        return new ReadOrderItemWrapper(
            responseDtoList,
            foundOrder.getTotalAmountSum(),
            foundOrder.getTotalPriceSum(),
            foundOrder.getId(),
            foundOrder.getOrderStatus()
        );
    }
}