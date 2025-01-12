package com.example.outsourcingproject.review.dto.request;

import lombok.Getter;

@Getter
public class CreateReviewRequestDto {
    private String contents;
    private Integer rating;
}
