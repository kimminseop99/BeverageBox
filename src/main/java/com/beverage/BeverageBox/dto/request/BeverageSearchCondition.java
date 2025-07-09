package com.beverage.BeverageBox.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeverageSearchCondition {
    private String keyword;       // 이름 검색용
    private Integer minPrice;     // 최저가
    private Integer maxPrice;     // 최고가
    private String sortBy;        // 정렬 기준: name, priceAsc, newest
}
