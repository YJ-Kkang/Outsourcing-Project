package com.example.outsourcingproject.review.service;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.entity.Review;
import com.example.outsourcingproject.order.repository.OrderRepository;
import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.repository.ReviewRepository;
import com.example.outsourcingproject.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl{
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public CreateReviewResponseDto createReviewService(
        Long orderId,
        CreateReviewRequestDto requestDto
    ) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 오더 식별자로 주문 조회했을 때 주문이 없으면 예외 처리

        Review reviewToSave = new Review(
            order.getId(),
            requestDto.getContents(),
            requestDto.getRating()
        );

        Review savedReview = reviewRepository.save(reviewToSave);
        Long savedReviewId = savedReview.getId();

        return new CreateReviewResponseDto (
            savedReviewId,
            requestDto.getContents(),
            requestDto.getRating()
        );
    }


}
