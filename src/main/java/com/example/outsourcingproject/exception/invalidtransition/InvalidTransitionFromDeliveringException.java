package com.example.outsourcingproject.exception.invalidtransition;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidTransitionFromDeliveringException extends InvalidTransitionException {

    public InvalidTransitionFromDeliveringException() {
        super(ErrorCode.INVALID_DELIVERING_STATE_TRANSITION);
    }
}
