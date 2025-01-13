package com.example.outsourcingproject.exception.badrequest;

import com.example.outsourcingproject.exception.ErrorCode;

public class CategoryInvalidCountException extends BadRequestException {

    public CategoryInvalidCountException() {
        super(ErrorCode.BAD_REQUEST_CATEGORY_INVALID_BOUND);
    }
}
