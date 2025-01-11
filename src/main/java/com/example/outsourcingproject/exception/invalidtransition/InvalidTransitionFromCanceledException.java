package com.example.outsourcingproject.exception.invalidtransition;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidTransitionFromCanceledException extends InvalidTransitionException {

    public InvalidTransitionFromCanceledException() {
        super(ErrorCode.INVALID_CANCELED_STATE_TRANSITION);
    }
}
