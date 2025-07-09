package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.entity.Beverage;
import com.beverage.BeverageBox.entity.CartItem;
import com.beverage.BeverageBox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByUserAndBeverage(User user, Beverage beverage);
    List<CartItem> findAllByUser(User user);
}
