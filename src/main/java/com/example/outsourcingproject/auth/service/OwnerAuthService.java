package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.request.SignInOwnerRequestDto;
import com.example.outsourcingproject.auth.dto.request.SignUpOwnerRequestDto;
import com.example.outsourcingproject.auth.dto.response.SignInOwnerResponseDto;
import com.example.outsourcingproject.auth.dto.response.SignUpOwnerResponseDto;

public interface OwnerAuthService {

    SignUpOwnerResponseDto signUp(SignUpOwnerRequestDto requestDto);

    SignInOwnerResponseDto signIn(SignInOwnerRequestDto requestDto);

    void deleteOwner(String password, String token);

}
