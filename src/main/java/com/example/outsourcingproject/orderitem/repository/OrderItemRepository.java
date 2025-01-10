package com.example.outsourcingproject.orderitem.repository;

import com.example.outsourcingproject.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
