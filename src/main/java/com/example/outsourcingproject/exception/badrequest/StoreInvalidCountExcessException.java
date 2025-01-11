package com.example.outsourcingproject.exception.badrequest;

import com.example.outsourcingproject.exception.ErrorCode;

public class StoreInvalidCountExcessException extends BadRequestException {

    public StoreInvalidCountExcessException() {
        super(ErrorCode.BAD_REQUEST_STORE_LIMIT);
    }
}
