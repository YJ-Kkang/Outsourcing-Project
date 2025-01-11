package com.example.outsourcingproject.exception.invalidtransition;

import com.example.outsourcingproject.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidTransitionException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidTransitionException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
