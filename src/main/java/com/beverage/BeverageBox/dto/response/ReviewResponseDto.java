package com.beverage.BeverageBox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewResponseDto {
    private Long reviewId;
    private Long beverageId;
    private String username;
    private String content;
    private Integer rating;
    private LocalDateTime createdAt;
}
