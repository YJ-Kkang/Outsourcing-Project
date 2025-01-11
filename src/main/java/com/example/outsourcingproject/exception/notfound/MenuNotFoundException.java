package com.example.outsourcingproject.exception.notfound;

import com.example.outsourcingproject.exception.ErrorCode;

public class MenuNotFoundException extends NotFoundException {

    public MenuNotFoundException() {
        super(ErrorCode.NOT_FOUND_MENU);
    }
}
