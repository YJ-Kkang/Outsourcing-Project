package com.example.outsourcingproject.exception.notfound;

import com.example.outsourcingproject.exception.ErrorCode;

public class OrderNotFoundException extends NotFoundException {

    public OrderNotFoundException() {
        super(ErrorCode.NOT_FOUND_ORDER);
    }
}
