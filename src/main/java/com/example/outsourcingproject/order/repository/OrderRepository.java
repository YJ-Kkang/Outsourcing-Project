package com.example.outsourcingproject.order.repository;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.order.OrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdAndOrderStatusNot(
        Long id,
        OrderStatus status
    );

    List<Order> findAllByStoreId(Long storeId);

}
