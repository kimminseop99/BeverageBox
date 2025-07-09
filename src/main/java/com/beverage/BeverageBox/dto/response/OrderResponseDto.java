package com.beverage.BeverageBox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private int totalPrice;
    private String status;
    private String shippingAddress;
    private List<OrderItemDto> orderItems;

    @Getter
    @AllArgsConstructor
    public static class OrderItemDto {
        private Long beverageId;
        private String beverageName;
        private int quantity;
        private int price;
    }
}
