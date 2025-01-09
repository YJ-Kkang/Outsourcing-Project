package com.example.outsourcingproject.auth.controller;

import com.example.outsourcingproject.auth.dto.request.SignInCustomerRequestDto;
import com.example.outsourcingproject.auth.dto.request.SignUpCustomerRequestDto;
import com.example.outsourcingproject.auth.dto.response.SignUpCustomerResponseDto;
import com.example.outsourcingproject.auth.service.CustomerAuthServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class CustomerAuthController {

    private final CustomerAuthServiceImpl customerAuthService;

    public CustomerAuthController(CustomerAuthServiceImpl customerAuthService) {
        this.customerAuthService = customerAuthService;
    }

    // 손님 회원가입
    @PostMapping("/auth/sign-up/customers")
    public ResponseEntity<SignUpCustomerResponseDto> signUp(
        @RequestBody SignUpCustomerRequestDto requestDto
    ) {
        SignUpCustomerResponseDto responseDto = customerAuthService.signUp(
            requestDto.getEmail(),
            requestDto.getPassword()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 손님 로그인
    // todo response 토큰
    @PostMapping("/auth/sign-in/customers")
    public ResponseEntity<Void> signIn(
        @RequestBody SignInCustomerRequestDto requestDto
    ) {
        customerAuthService.signIn(
            requestDto.getEmail(),
            requestDto.getPassword()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
