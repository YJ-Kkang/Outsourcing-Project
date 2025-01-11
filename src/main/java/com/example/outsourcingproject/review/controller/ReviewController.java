package com.example.outsourcingproject.review.controller;

import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.dto.response.FindReviewResponseDto;
import com.example.outsourcingproject.review.service.ReviewServiceImpl;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        @Valid @RequestBody CreateReviewRequestDto requestDto,
        @RequestHeader("Authorization") String token //todo @Valid 유효성 검사
    ) {
        CreateReviewResponseDto responseDto = reviewServiceImpl.createReviewService(
            orderId,
            requestDto,
            token
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 리뷰 다건 조회(가게 정보 기준, 최신순 정렬)
    // 리뷰 별점 범위에 따라 조회 가능
    @GetMapping
    public List<FindReviewResponseDto> findAllReview() {
    List<FindReviewResponseDto> findAllFindReviewResponseDtoList = reviewServiceImpl.findAllReviewService();
    return findAllFindReviewResponseDtoList;
    }

}
