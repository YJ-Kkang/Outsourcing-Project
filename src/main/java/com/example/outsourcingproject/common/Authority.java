package com.example.outsourcingproject.common;

public enum Authority {
    CUSTOMER("CUSTOMER"),
    OWNER("OWNER");

    private final String value;

    Authority(String value) {
        this.value = value;
    }
}
