package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.CartItemRequestDto;
import com.beverage.BeverageBox.dto.response.CartItemResponseDto;
import com.beverage.BeverageBox.service.CartItemService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    // ✅ 장바구니 담기
    @PostMapping
    public void addToCart(@Valid @RequestBody CartItemRequestDto dto, HttpSession session) {
        Long userId = getSessionUserId(session);
        cartItemService.addToCart(userId, dto);
    }

    // ✅ 장바구니 목록 조회
    @GetMapping
    public List<CartItemResponseDto> getCartItems(HttpSession session) {
        Long userId = getSessionUserId(session);
        return cartItemService.getCartItems(userId);
    }

    // ✅ 수량 변경
    @PutMapping("/{cartItemId}")
    public void updateQuantity(@PathVariable Long cartItemId,
                               @RequestParam int quantity,
                               HttpSession session) {
        Long userId = getSessionUserId(session);
        cartItemService.updateCartItem(userId, cartItemId, quantity);
    }

    // ✅ 장바구니 항목 삭제
    @DeleteMapping("/{cartItemId}")
    public void removeItem(@PathVariable Long cartItemId, HttpSession session) {
        Long userId = getSessionUserId(session);
        cartItemService.removeCartItem(userId, cartItemId);
    }

    // 🔒 로그인 사용자 확인용 유틸
    private Long getSessionUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return userId;
    }
}

