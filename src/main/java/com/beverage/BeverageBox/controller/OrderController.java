package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.OrderRequestDto;
import com.beverage.BeverageBox.dto.response.OrderResponseDto;
import com.beverage.BeverageBox.service.OrderService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성 (장바구니 기반)
    @PostMapping
    public void createOrder(@Valid @RequestBody OrderRequestDto dto, HttpSession session) {
        Long userId = getSessionUserId(session);
        orderService.createOrder(userId, dto);
    }

    @GetMapping
    public List<OrderResponseDto> getOrders(HttpSession session) {
        Long userId = getSessionUserId(session);
        return orderService.getOrdersByUser(userId);
    }


    // 세션에서 로그인 유저 ID 가져오기 (로그인 필수)
    private Long getSessionUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return userId;
    }
}
