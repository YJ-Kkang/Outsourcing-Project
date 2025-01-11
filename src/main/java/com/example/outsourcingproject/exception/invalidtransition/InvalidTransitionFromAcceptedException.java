package com.example.outsourcingproject.exception.invalidtransition;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidTransitionFromAcceptedException extends InvalidTransitionException {

    public InvalidTransitionFromAcceptedException() {
        super(ErrorCode.INVALID_ACCEPTED_STATE_TRANSITION);
    }
}
