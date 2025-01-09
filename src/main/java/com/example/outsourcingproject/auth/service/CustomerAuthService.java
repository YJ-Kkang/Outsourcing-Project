package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignInCustomerResponseDto;
import com.example.outsourcingproject.auth.dto.response.SignUpCustomerResponseDto;

public interface CustomerAuthService {

    SignUpCustomerResponseDto signUp(String email, String password);

    SignInCustomerResponseDto signIn(String email, String password);

}
