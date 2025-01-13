package com.example.outsourcingproject.auth.dto.response;

import lombok.Getter;

@Getter
public class SignInOwnerResponseDto {

    private final String token;

    public SignInOwnerResponseDto(String token) {
        this.token = token;
    }
}
