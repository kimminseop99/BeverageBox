package com.beverage.BeverageBox.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BeverageRequestDto {
    private String name;
    private String description;
    private int price;
    private int stock;
    private String imageUrl;
}
