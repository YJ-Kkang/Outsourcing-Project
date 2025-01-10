package com.example.outsourcingproject.review.dto.response;

import lombok.Getter;

@Getter
public class CreateReviewResponseDto {
    private final Long id;
    private final String contents;
    private final Integer rating;

    public CreateReviewResponseDto(Long id, String contents, Integer rating) {
        this.id = id;
        this.contents = contents;
        this.rating = rating;
    }


}
