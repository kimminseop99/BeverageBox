package com.beverage.BeverageBox.service;

import com.beverage.BeverageBox.dto.request.CartItemRequestDto;
import com.beverage.BeverageBox.dto.response.CartItemResponseDto;
import com.beverage.BeverageBox.entity.Beverage;
import com.beverage.BeverageBox.entity.CartItem;
import com.beverage.BeverageBox.entity.User;
import com.beverage.BeverageBox.repository.BeverageRepository;
import com.beverage.BeverageBox.repository.CartItemRepository;
import com.beverage.BeverageBox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final BeverageRepository beverageRepository;

    /**
     * 장바구니에 상품 추가
     */
    @Transactional
    public void addToCart(Long userId, CartItemRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));

        Beverage beverage = beverageRepository.findById(dto.getBeverageId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음료입니다."));

        // 이미 담긴 항목이 있으면 수량 증가
        Optional<CartItem> optionalItem = cartItemRepository.findByUserAndBeverage(user, beverage);

        if (optionalItem.isPresent()) {
            CartItem existingItem = optionalItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + dto.getQuantity());
        } else {
            CartItem newItem = CartItem.builder()
                    .user(user)
                    .beverage(beverage)
                    .quantity(dto.getQuantity())
                    .build();
            cartItemRepository.save(newItem);
        }
    }

    /**
     * 로그인한 사용자의 장바구니 조회
     */
    @Transactional(readOnly = true)
    public List<CartItemResponseDto> getCartItems(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));

        List<CartItem> cartItems = cartItemRepository.findAllByUser(user);

        return cartItems.stream().map(item -> new CartItemResponseDto(
                item.getId(),
                item.getBeverage().getId(),
                item.getBeverage().getName(),
                item.getQuantity(),
                item.getBeverage().getPrice()
        )).collect(Collectors.toList());
    }

    /**
     * 장바구니 항목 수량 변경
     */
    @Transactional
    public void updateCartItem(Long userId, Long cartItemId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목이 없습니다."));

        if (!item.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인의 장바구니 항목만 수정할 수 있습니다.");
        }

        item.setQuantity(quantity);
    }

    /**
     * 장바구니 항목 삭제
     */
    @Transactional
    public void removeCartItem(Long userId, Long cartItemId) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("장바구니 항목이 없습니다."));

        if (!item.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인의 장바구니 항목만 삭제할 수 있습니다.");
        }

        cartItemRepository.delete(item);
    }
}

