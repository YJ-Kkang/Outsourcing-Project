package com.example.outsourcingproject.exception.badrequest;

import com.example.outsourcingproject.exception.ErrorCode;

public class InvalidOrderTimeException extends BadRequestException {

    public InvalidOrderTimeException() {
        super(ErrorCode.BAD_REQUEST_INVALID_ORDER_TIME);
    }
}
