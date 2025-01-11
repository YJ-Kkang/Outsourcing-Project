package com.example.outsourcingproject.exception.notfound;

import com.example.outsourcingproject.exception.ErrorCode;

public class StoreNotFoundException extends NotFoundException {

    public StoreNotFoundException() {
        super(ErrorCode.NOT_FOUND_STORE);
    }
}
