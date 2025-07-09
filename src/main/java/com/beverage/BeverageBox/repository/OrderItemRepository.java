package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}