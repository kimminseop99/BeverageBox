package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.entity.Order;
import com.beverage.BeverageBox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
