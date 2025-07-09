package com.beverage.BeverageBox.controller;

import com.beverage.BeverageBox.dto.request.ReviewRequestDto;
import com.beverage.BeverageBox.dto.response.ReviewResponseDto;
import com.beverage.BeverageBox.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private Long getSessionUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("LOGIN_USER");
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        return userId;
    }

    @PostMapping
    public ReviewResponseDto createReview(@Valid @RequestBody ReviewRequestDto dto, HttpSession session) {
        Long userId = getSessionUserId(session);
        return reviewService.createReview(userId, dto);
    }

    @GetMapping("/beverage/{beverageId}")
    public List<ReviewResponseDto> getReviewsByBeverage(@PathVariable Long beverageId) {
        return reviewService.getReviewsByBeverage(beverageId);
    }

    @PutMapping("/{reviewId}")
    public ReviewResponseDto updateReview(@PathVariable Long reviewId,
                                          @Valid @RequestBody ReviewRequestDto dto,
                                          HttpSession session) {
        Long userId = getSessionUserId(session);
        return reviewService.updateReview(userId, reviewId, dto);
    }

    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId, HttpSession session) {
        Long userId = getSessionUserId(session);
        reviewService.deleteReview(userId, reviewId);
    }
}
