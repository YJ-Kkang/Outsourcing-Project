package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignUpCustomersResponseDto;

public interface CustomerAuthService {
    SignUpCustomersResponseDto signUp(String email, String password);

    void signIn(String email, String password);


}
