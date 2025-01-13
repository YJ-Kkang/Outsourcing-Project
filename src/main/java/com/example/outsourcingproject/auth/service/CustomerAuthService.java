package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.request.SignInCustomerRequestDto;
import com.example.outsourcingproject.auth.dto.request.SignUpCustomerRequestDto;
import com.example.outsourcingproject.auth.dto.response.SignInCustomerResponseDto;
import com.example.outsourcingproject.auth.dto.response.SignUpCustomerResponseDto;

public interface CustomerAuthService {

    SignUpCustomerResponseDto signUp(SignUpCustomerRequestDto requestDto);

    SignInCustomerResponseDto signIn(SignInCustomerRequestDto requestDto);

    void deleteCustomer(String password, String token);

}
