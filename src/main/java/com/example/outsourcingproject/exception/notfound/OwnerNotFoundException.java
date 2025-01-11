package com.example.outsourcingproject.exception.notfound;

import com.example.outsourcingproject.exception.ErrorCode;

public class OwnerNotFoundException extends NotFoundException {

    public OwnerNotFoundException() {
        super(ErrorCode.NOT_FOUND_OWNER);
    }
}