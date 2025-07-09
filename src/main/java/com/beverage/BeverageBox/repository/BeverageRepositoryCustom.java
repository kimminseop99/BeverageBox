package com.beverage.BeverageBox.repository;

import com.beverage.BeverageBox.dto.request.BeverageSearchCondition;
import com.beverage.BeverageBox.entity.Beverage;

import java.util.List;

public interface BeverageRepositoryCustom {
    List<Beverage> searchWithCondition(BeverageSearchCondition condition);
}
