package com.example.outsourcingproject.auth.dto.request;

import lombok.Getter;

@Getter
public class SignUpOwnerRequestDto {

    // todo 사용자 아이디는 이메일 형식
    private final String email;

    // todo 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함, 최소 8글자 이상
    private final String password;

    public SignUpOwnerRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
