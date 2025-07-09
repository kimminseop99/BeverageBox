package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.entity.Beverage;
import com.beverage.BeverageBox.entity.Review;
import com.beverage.BeverageBox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBeverage(Beverage beverage);
    Optional<Review> findByUserAndBeverage(User user, Beverage beverage);
}
