package com.example.outsourcingproject.exception.invalidtransition;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidPendingTransitionException extends InvalidTransitionException {

    public InvalidPendingTransitionException() {
        super(ErrorCode.INVALID_PENDING_TRANSITION);
    }
}