package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignUpCustomerResponseDto;

public interface CustomerAuthService {

    SignUpCustomerResponseDto signUp(String email, String password);

    void signIn(String email, String password);

}
