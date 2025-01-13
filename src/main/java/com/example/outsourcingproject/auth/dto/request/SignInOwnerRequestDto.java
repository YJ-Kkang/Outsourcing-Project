package com.example.outsourcingproject.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignInOwnerRequestDto {

    @Email
    @NotBlank
    private final String email;

    // 비밀번호 형식 : 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[a-zA-Z\\d@$!%*?&]{8,}$")
    @Size(min = 8)
    private final String password;

    public SignInOwnerRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
