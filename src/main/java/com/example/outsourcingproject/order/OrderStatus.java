package com.example.outsourcingproject.order;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum OrderStatus {
    PENDING, // 주문 생성 시, 즉 수락 대기 상태
    ACCEPTED, // 주문 수락
    CANCELED, // 주문 취소
    DELIVERING, // ACCEPTED 이후 배달 중
    DELIVERED; // DELIVERING 이후 배달 완료

    // 기능: 입력된 문자열을 바탕으로 OrderStatus 값을 찾는 메서드
    public static OrderStatus of(String orderStatus) {
        return Arrays.stream(OrderStatus.values())
            .filter(
                status -> status
                    .name()
                    .equalsIgnoreCase(orderStatus))
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST
                )
            ); // todo 상태 값이 없을 시 예외 처리
    }
}