package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignUpOwnerResponseDto;

public interface OwnerAuthService {
    SignUpOwnerResponseDto signUp(String email, String password);

    void signIn(String email, String password);

}
