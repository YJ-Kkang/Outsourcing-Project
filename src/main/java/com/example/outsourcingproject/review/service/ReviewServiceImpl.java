package com.example.outsourcingproject.review.service;

import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.entity.Review;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.order.repository.OrderRepository;
import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.repository.ReviewRepository;
import com.example.outsourcingproject.store.repository.StoreRepository;
import java.time.LocalDateTime;
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
        // 리뷰 데이터를 저장하기 위한 사전 작업
        Order foundorder = orderRepository.findById(orderId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 오더 식별자로 주문 조회했을 때 주문이 없으면 예외 처리

        Store foundStore = foundorder.getStore();

        // (1) 오더 객체에 유저아이디 없음 | (2) 토큰에서 유저아이디 어떻게 뽑아올 수 있는지 묻기
        // 토큰 안에 유저에 대한 정보가 담겨 있을 수 있다. 여기서 유저아이디 가져와서 넣어주는 게 좋은지,
        // 아니면 오더라는 객체 통해서 유저아이디 넣어주는 게 좋은지 고민하기...
        // -> todo 아직 인증인가 부분 해결 안돼서 즉각 적용 어렵다고 함. 추후에 보완하기

        // Dto에서 리뷰 내용 가져오고, 리뷰 엔티티 만들어주기(엔티티 == 테이블)
        Review reviewToSave = new Review(
            foundCustomer, // todo 인증인가 부분 해결되면 넣기
            foundStore,
            foundorder,
            requestDto.getContents(),
            requestDto.getRating()
        );

        // 리뷰 저장하기
        Review savedReview = reviewRepository.save(reviewToSave);

        // 저장된 리뷰 데이터에서 값 추출하기
        Long savedReviewId = savedReview.getId();
        LocalDateTime savedLocalDateTime = savedReview.getCreatedAt();


        return new CreateReviewResponseDto (
            savedReviewId,
            requestDto.getContents(),
            requestDto.getRating(),
            savedLocalDateTime
        );
    }


}
