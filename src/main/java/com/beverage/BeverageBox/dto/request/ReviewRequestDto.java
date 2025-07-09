package com.beverage.BeverageBox.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewRequestDto {
    private Long beverageId;
    private String content;
    private int rating; // 1~5
}

