package com.beverage.BeverageBox.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemRequestDto {
    private Long beverageId;
    private int quantity;
}

