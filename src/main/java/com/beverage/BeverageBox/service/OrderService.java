package com.beverage.BeverageBox.service;

import com.beverage.BeverageBox.dto.request.OrderRequestDto;
import com.beverage.BeverageBox.entity.*;
import com.beverage.BeverageBox.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final BeverageRepository beverageRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     * 장바구니 기반으로 주문 생성
     */
    @Transactional
    public void createOrder(Long userId, OrderRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));

        List<CartItem> cartItems = cartItemRepository.findAllByUser(user);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("장바구니가 비어 있습니다.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        int totalPrice = 0;

        // 재고 확인 및 총액 계산
        for (CartItem cartItem : cartItems) {
            Beverage beverage = cartItem.getBeverage();
            int orderQty = cartItem.getQuantity();

            if (beverage.getStock() < orderQty) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다: " + beverage.getName());
            }

            beverage.setStock(beverage.getStock() - orderQty); // 재고 차감

            OrderItem orderItem = OrderItem.builder()
                    .beverage(beverage)
                    .quantity(orderQty)
                    .price(beverage.getPrice())
                    .build();

            orderItems.add(orderItem);
            totalPrice += orderQty * beverage.getPrice();
        }

        // 주문 생성
        Order order = Order.builder()
                .user(user)
                .orderDate(LocalDateTime.now())
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .shippingAddress(dto.getShippingAddress())
                .build();

        orderRepository.save(order);

        for (OrderItem item : orderItems) {
            item.setOrder(order); // 연관관계 설정
            orderItemRepository.save(item);
        }

        cartItemRepository.deleteAll(cartItems); // 장바구니 초기화
    }
}
