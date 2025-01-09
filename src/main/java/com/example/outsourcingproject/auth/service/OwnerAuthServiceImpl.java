package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignUpOwnerResponseDto;
import com.example.outsourcingproject.auth.repository.OwnerAuthRepository;
import com.example.outsourcingproject.config.PasswordEncoder;
import com.example.outsourcingproject.config.error.CustomException;
import com.example.outsourcingproject.config.error.ErrorCode;
import com.example.outsourcingproject.entity.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OwnerAuthServiceImpl implements OwnerAuthService{

    private final OwnerAuthRepository ownerAuthRepository;
    PasswordEncoder bcrypt = new PasswordEncoder();

    public OwnerAuthServiceImpl(OwnerAuthRepository ownerAuthRepository) {
        this.ownerAuthRepository = ownerAuthRepository;
    }

    @Override
    public SignUpOwnerResponseDto signUp(String email, String password) {

        // 등록된 아이디(이메일) 여부 확인
        boolean isExistEmail = ownerAuthRepository.findByEmail(email).isPresent();

        if(isExistEmail) {
            log.info("이미 존재하는 이메일입니다. >> {}", email);
            throw new CustomException(ErrorCode.EMAIL_EXIST);
        }
        Owner owner = new Owner(email, bcrypt.encode(password));
        Owner savedOwner = ownerAuthRepository.save(owner);

        log.info("사장님 {} 회원가입 완료", email);
        return new SignUpOwnerResponseDto(savedOwner);
    }

    @Override
    public void signIn(String email, String rawPassword) {
        // todo 반환

        Owner owner = ownerAuthRepository.findByEmail(email)
            .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        String encodedPassword = owner.getPassword();

        boolean isPasswordMisMatching = !bcrypt.matches(rawPassword, encodedPassword);

        if(isPasswordMisMatching){
            log.info("아이디 또는 비밀번호가 잘못되었습니다.");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        log.info("사장님 {} 로그인 완료", email);
        // todo return 사장님 토큰
    }
}
