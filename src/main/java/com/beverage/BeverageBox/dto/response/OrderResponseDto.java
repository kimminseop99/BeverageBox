package com.beverage.BeverageBox.dto.response;

import com.beverage.BeverageBox.dto.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private LocalDateTime orderDate;
    private int totalPrice;
    private String status;
    private List<OrderItemDto> items;
    private String shippingAddress;
}

