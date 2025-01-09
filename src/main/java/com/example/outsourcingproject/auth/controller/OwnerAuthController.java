package com.example.outsourcingproject.auth.controller;

import com.example.outsourcingproject.auth.dto.request.SignInOwnerRequestDto;
import com.example.outsourcingproject.auth.dto.request.SignUpOwnerRequestDto;
import com.example.outsourcingproject.auth.dto.response.SignUpOwnerResponseDto;
import com.example.outsourcingproject.auth.service.OwnerAuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OwnerAuthController {

    private final OwnerAuthServiceImpl ownerAuthService;

    public OwnerAuthController(OwnerAuthServiceImpl ownerAuthService) {
        this.ownerAuthService = ownerAuthService;
    }

    // 사장님 회원가입
    @PostMapping("/auth/sign-up/owners")
    public ResponseEntity<SignUpOwnerResponseDto> signUp(
        @RequestBody SignUpOwnerRequestDto requestDto
    ) {
        SignUpOwnerResponseDto responseDto = ownerAuthService.signUp(
            requestDto.getEmail(),
            requestDto.getPassword()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 사장님 로그인
    // todo response 토큰
    @PostMapping("/auth/sign-in/owners")
    public ResponseEntity<Void> signIn(
        @RequestBody SignInOwnerRequestDto requestDto
    ) {

        ownerAuthService.signIn(
            requestDto.getEmail(),
            requestDto.getPassword()
        );

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
