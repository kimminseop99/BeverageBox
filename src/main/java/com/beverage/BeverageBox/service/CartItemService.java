package com.beverage.BeverageBox.service;

import com.beverage.BeverageBox.dto.request.CartItemRequestDto;
import com.beverage.BeverageBox.dto.response.CartItemResponseDto;
import com.beverage.BeverageBox.entity.User;

import java.util.List;

public class CartItemService {
    public void addToCart(CartItemRequestDto dto, User user) { }
    public List<CartItemResponseDto> getMyCart(User user) { return null; }
    public void updateQuantity(Long cartItemId, int quantity, User user) { }
    public void removeFromCart(Long cartItemId, User user) { }
}

