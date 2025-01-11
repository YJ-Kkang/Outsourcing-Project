package com.example.outsourcingproject.exception.invalidtransition;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidTransitionFromPendingException extends InvalidTransitionException {

    public InvalidTransitionFromPendingException() {
        super(ErrorCode.INVALID_PENDING_STATE_TRANSITION);
    }
}