package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.dto.request.BeverageSearchCondition;
import com.beverage.BeverageBox.entity.Beverage;
import com.beverage.BeverageBox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeverageRepository extends JpaRepository<Beverage, Long>,  BeverageRepositoryCustom {

}
