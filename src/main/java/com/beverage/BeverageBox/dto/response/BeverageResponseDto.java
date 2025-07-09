package com.beverage.BeverageBox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeverageResponseDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private String imageUrl;
}

