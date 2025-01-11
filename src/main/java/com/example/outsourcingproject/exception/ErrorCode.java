package com.example.outsourcingproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ERR001", "아이디 또는 비밀번호가 잘못되었습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "ERR002", "올바르지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "ERR003", "잘못된 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERR004", "서버 에러가 발생했습니다."),
    NOT_FOUND_OWNER(HttpStatus.NOT_FOUND, "ERROR05", "존재하지 않는 사장님입니다."),
    NOT_FOUND_CUSTOMER(HttpStatus.NOT_FOUND, "ERR006", "존재하지 않는 손님입니다."),
    NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "ERR007", "존재하지 않는 가게입니다."),
    NOT_FOUND_MENU(HttpStatus.NOT_FOUND, "ERR008", "존재하지 않는 메뉴입니다."),
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "ERR009", "존재하지 않는 주문입니다."),
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "ERR0010", "존재하지 않는 리뷰입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ERR0011", "접근 권한이 없습니다."),
    EMAIL_EXIST(HttpStatus.BAD_REQUEST, "ERR0012", "이미 존재하거나 탈퇴한 이메일입니다."),
    INVALID_PENDING_STATE_TRANSITION(HttpStatus.CONFLICT,"ERR0013", "대기 중 상태에서 이 작업을 수행할 수 없습니다. 주문을 먼저 수락해주세요."),
    INVALID_ACCEPTED_STATE_TRANSITION(HttpStatus.CONFLICT,"ERR0014", "수락 상태에서 이 작업을 수행할 수 없습니다. 주문은 수락 후 배달 중 상태로만 변경 가능합니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(
        HttpStatus status,
        String code,
        String message
    ) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
