package com.example.outsourcingproject.review.controller;

import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.service.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final ReviewServiceImpl reviewServiceImpl;

    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @PostMapping("/orders/{orderId}/reviews")
    public ResponseEntity<CreateReviewResponseDto> createReviewAPI(
        @PathVariable("orderId") Long orderId,
        @RequestBody CreateReviewRequestDto requestDto
    ) {
        CreateReviewResponseDto responseDto = reviewServiceImpl.createReviewService(
            orderId,
            requestDto
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
