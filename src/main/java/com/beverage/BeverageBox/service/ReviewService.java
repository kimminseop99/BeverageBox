package com.beverage.BeverageBox.service;

import com.beverage.BeverageBox.dto.request.ReviewRequestDto;
import com.beverage.BeverageBox.dto.response.ReviewResponseDto;
import com.beverage.BeverageBox.entity.*;
import com.beverage.BeverageBox.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BeverageRepository beverageRepository;

    /**
     * 리뷰 작성
     */
    @Transactional
    public ReviewResponseDto createReview(Long userId, ReviewRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보가 없습니다."));

        Beverage beverage = beverageRepository.findById(dto.getBeverageId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음료입니다."));

        // 중복 리뷰 방지 (한 유저당 한 상품에 리뷰 하나만 허용)
        boolean exists = reviewRepository.existsByUserAndBeverage(user, beverage);
        if (exists) {
            throw new IllegalArgumentException("이미 작성한 리뷰가 존재합니다.");
        }

        Review review = Review.builder()
                .user(user)
                .beverage(beverage)
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();

        reviewRepository.save(review);

        return new ReviewResponseDto(
                review.getId(),
                beverage.getId(),
                user.getUsername(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    /**
     * 상품별 리뷰 목록 조회
     */
    @Transactional(readOnly = true)
    public List<ReviewResponseDto> getReviewsByBeverage(Long beverageId) {
        Beverage beverage = beverageRepository.findById(beverageId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 음료입니다."));

        List<Review> reviews = reviewRepository.findAllByBeverageOrderByCreatedAtDesc(beverage);

        return reviews.stream()
                .map(r -> new ReviewResponseDto(
                        r.getId(),
                        beverageId,
                        r.getUser().getUsername(),
                        r.getContent(),
                        r.getRating(),
                        r.getCreatedAt()
                )).collect(Collectors.toList());
    }

    /**
     * 리뷰 수정 (작성자 본인만 가능)
     */
    @Transactional
    public ReviewResponseDto updateReview(Long userId, Long reviewId, ReviewRequestDto dto) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인 리뷰만 수정할 수 있습니다.");
        }

        review.setContent(dto.getContent());
        review.setRating(dto.getRating());

        return new ReviewResponseDto(
                review.getId(),
                review.getBeverage().getId(),
                review.getUser().getUsername(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    /**
     * 리뷰 삭제 (작성자 본인만 가능)
     */
    @Transactional
    public void deleteReview(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));

        if (!review.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("본인 리뷰만 삭제할 수 있습니다.");
        }

        reviewRepository.delete(review);
    }
}
