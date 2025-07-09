package com.beverage.BeverageBox.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private String username;
    private Long beverageId;
    private String content;
    private int rating;
    private LocalDateTime createdAt;
}

