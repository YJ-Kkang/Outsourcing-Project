package com.example.outsourcingproject.auth.dto.response;

import lombok.Getter;

@Getter
public class SignInCustomerResponseDto {
    private final String token;

    public SignInCustomerResponseDto(String token) {
        this.token = token;
    }
}
