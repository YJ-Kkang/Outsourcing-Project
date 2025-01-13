package com.example.outsourcingproject.exception.badrequest;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidOrderStateException extends BadRequestException {

    public InvalidOrderStateException() {
        super(ErrorCode.BAD_REQUEST_INVALID_ORDER_STATE);
    }
}
