package com.example.outsourcingproject.review.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CreateReviewResponseDto {
    private final Long id;
    private final String contents;
    private final Integer rating;
    private final LocalDateTime createdAt;

    public CreateReviewResponseDto(Long id, String contents, Integer rating, LocalDateTime createdAt) {
        this.id = id;
        this.contents = contents;
        this.rating = rating;
        this.createdAt = createdAt;
    }


}
