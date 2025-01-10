package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.request.SignUpCustomerRequestDto;
import com.example.outsourcingproject.auth.dto.response.SignInCustomerResponseDto;
import com.example.outsourcingproject.auth.dto.response.SignUpCustomerResponseDto;

public interface CustomerAuthService {

    SignUpCustomerResponseDto signUp(SignUpCustomerRequestDto requestDto);

    SignInCustomerResponseDto signIn(String email, String password);

    void deleteCustomer(String password, String token);

}
