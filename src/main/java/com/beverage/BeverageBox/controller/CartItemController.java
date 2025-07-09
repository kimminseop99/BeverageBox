package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.CartItemRequestDto;
import com.beverage.BeverageBox.dto.response.CartItemResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemController {
    @PostMapping
    public void addToCart(@RequestBody CartItemRequestDto dto) { }
    @GetMapping
    public List<CartItemResponseDto> myCart() { return null; }
}

