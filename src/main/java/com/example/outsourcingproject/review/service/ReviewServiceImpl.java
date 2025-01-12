package com.example.outsourcingproject.review.service;

import com.example.outsourcingproject.auth.repository.CustomerAuthRepository;
import com.example.outsourcingproject.entity.BaseEntity;
import com.example.outsourcingproject.entity.Customer;
import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.entity.Review;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.order.OrderStatus;
import com.example.outsourcingproject.order.repository.OrderRepository;
import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.dto.response.FindReviewResponseDto;
import com.example.outsourcingproject.review.repository.ReviewRepository;
import com.example.outsourcingproject.utils.JwtUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final CustomerAuthRepository customerAuthRepository;
    private final JwtUtil jwtUtil;

    // 리뷰 생성
    @Transactional
    public CreateReviewResponseDto createReviewService(
        Long orderId,
        CreateReviewRequestDto requestDto,
        String token
    ) {
        // 리뷰 데이터를 저장하기 위한 사전 작업
        // 주문 조회
        Order findorder = orderRepository
            .findById(orderId)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)// todo 오더 식별자로 주문 조회했을 때 주문이 없으면 예외 처리
            );

        // 주문 상태가 배송 완료일 경우에만 리뷰를 쓸 수 있도록 예외처리
        boolean isNotDelivered = !findorder
            .getOrderStatus()
            .equals(OrderStatus.DELIVERED
            );

        if (isNotDelivered) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN); // todo OrderStatus 배송완료 아닌 경우 예외 처리
        }

        // 가게 정보 가져오기
        Store findStore = findorder.getStore();

        // jwt 토큰에 저장된 손님 이메일 추출
        String customerEmail = jwtUtil.extractCustomerEmail(token);

        // 손님 이메일로 손님 아이디 추출
        Customer findCustomer = customerAuthRepository
            .findByEmail(customerEmail)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                // todo 손님 이메일로 찾을 수 있는 아이디 없으면 예외 처리
            );

        // Dto 에서 리뷰 내용 가져오고, 리뷰 엔티티 만들어주기(엔티티 == 테이블)
        Review reviewToSave = new Review(
            findCustomer,
            findStore,
            findorder,
            requestDto.getContents(),
            requestDto.getRating()
        );

        // 리뷰 저장하기
        Review savedReview = reviewRepository.save(reviewToSave);

        // 저장된 리뷰 데이터에서 값 추출하기
        Long savedReviewId = savedReview.getId();
        LocalDateTime savedLocalDateTime = savedReview.getCreatedAt();

        return new CreateReviewResponseDto(
            savedReviewId,
            requestDto.getContents(),
            requestDto.getRating(),
            savedLocalDateTime
        );
    }

    // 리뷰 다건 조회
    public List<FindReviewResponseDto> findAllReviewService(
        Long storeId,
        String sort,
        Integer startRating,
        Integer endRating
    ) {
        // 별점 필터링 조건 처리
        List<Review> reviewList = new ArrayList<>();
        if (startRating != null && endRating != null) {
            // 별점 범위 지정된 경우
            reviewList = reviewRepository
                .findByStoreIdAndRatingBetween(
                    storeId,
                    startRating,
                    endRating
                );
        }

        if (startRating != null && endRating == null) {
            // startRating 만 지정된 경우
            reviewList = reviewRepository
                .findByStoreIdAndRatingGreaterThanEqual(
                    storeId,
                    startRating
                );
        }

        if (startRating == null && endRating != null) {
            // endRating만 지정된 경우
            reviewList = reviewRepository
                .findByStoreIdAndRatingLessThanEqual(
                    storeId,
                    endRating
                );
        }

        if (startRating == null && endRating == null) {
            // 별점 범위 지정되지 않은 경우 모든 리뷰 가져오기
            reviewList = reviewRepository.findByStoreId(storeId);
        }

        // 정렬 조건 적용
        if ("latest".equals(sort)) {
            // 최신순 정렬
            reviewList.sort((r1, r2) -> r2.getCreatedAt().compareTo(r1.getCreatedAt()));
        } else if ("oldest".equals(sort)) {
            // 오래된순 정렬
            reviewList.sort(Comparator.comparing(BaseEntity::getCreatedAt));
        }

        // Review 엔티티를 DTO 로 변환
        List<FindReviewResponseDto> findReviewResponseDtoList = new ArrayList<>();
        for (Review review : reviewList) {
            FindReviewResponseDto findReviewResponseDto = new FindReviewResponseDto(review);
            findReviewResponseDtoList.add(findReviewResponseDto);
        }

        // 해당 가게에 리뷰가 존재하지 않을 경우 예외처리
        if (findReviewResponseDtoList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // todo 해당 가게에 대한 리뷰가 없을 경우 예외처리
        }

        return findReviewResponseDtoList;
    }
}
