package com.example.outsourcingproject.review.controller;

import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.service.ReviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        @RequestBody CreateReviewRequestDto requestDto,
        @RequestHeader("Authorization") String token //todo @Valid 유효성 검사
        // @RequestAttribute String email 또는 @RequestAttribute Long userId하면 바로 값(이메일 또는 유저 아이디) 받아진다
        // 이 받은 값을 매개변수로 서비스로 넘겨주면 바로 쓸 수 있다.
    ) {
        CreateReviewResponseDto responseDto = reviewServiceImpl.createReviewService(
            orderId,
            requestDto,
            token
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
