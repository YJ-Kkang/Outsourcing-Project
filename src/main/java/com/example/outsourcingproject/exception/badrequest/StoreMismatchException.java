package com.example.outsourcingproject.exception.badrequest;

import com.example.outsourcingproject.exception.ErrorCode;

public class StoreMismatchException extends BadRequestException {

    public StoreMismatchException() {
        super(ErrorCode.BAD_REQUEST_STORE_MISMATCH);
    }
}
