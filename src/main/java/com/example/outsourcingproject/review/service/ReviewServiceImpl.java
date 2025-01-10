package com.example.outsourcingproject.review.service;

import com.example.outsourcingproject.auth.repository.CustomerAuthRepository;
import com.example.outsourcingproject.entity.Customer;
import com.example.outsourcingproject.entity.Order;
import com.example.outsourcingproject.entity.Review;
import com.example.outsourcingproject.entity.Store;
import com.example.outsourcingproject.order.OrderStatus;
import com.example.outsourcingproject.order.repository.OrderRepository;
import com.example.outsourcingproject.review.dto.request.CreateReviewRequestDto;
import com.example.outsourcingproject.review.dto.response.CreateReviewResponseDto;
import com.example.outsourcingproject.review.repository.ReviewRepository;
import com.example.outsourcingproject.store.repository.StoreRepository;
import com.example.outsourcingproject.utils.JwtUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    private final CustomerAuthRepository customerAuthRepository;
    private final JwtUtil jwtUtil;

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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 오더 식별자로 주문 조회했을 때 주문이 없으면 예외 처리

        // 주문 상태가 배송 완료일 경우에만 리뷰를 쓸 수 있도록 예외처리
        boolean isNotDeliverd = !findorder
            .getOrderStatus()
            .equals(OrderStatus.DELIVERED
            );

        if (isNotDeliverd) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // todo OrderStatus가 배송완료 아닌 경우 예외 처리
        }

        // 가게 정보 가져오기
        Store findStore = findorder.getStore();

        // jwt 토큰에 저장된 손님 이메일 추출
        String customerEmail = jwtUtil.extractCustomerEmail(token);

        // 손님 이메일로 손님 아이디 추출
        Customer findCustomer = customerAuthRepository
            .findByEmail(customerEmail)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            ); // todo 손님 이메일로 찾을 수 있는 아이디 없으면 예외 처리

        // Dto에서 리뷰 내용 가져오고, 리뷰 엔티티 만들어주기(엔티티 == 테이블)
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

    public List<CreateReviewResponseDto> findAllReviewService() {
        // DB에서 가져오기
        List<Review> reviewList = new ArrayList<>();
        reviewList = reviewRepository
            .findAll();

        // dtoList로 변환하기 위해 선언
        List<CreateReviewResponseDto> createReviewResponseDtoList = new ArrayList<>();

        for (Review review : reviewList) {
            CreateReviewResponseDto createReviewResponseDto = new CreateReviewResponseDto(reviewList);

        }
    }
}
