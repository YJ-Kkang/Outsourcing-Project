package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignInOwnerResponseDto;
import com.example.outsourcingproject.auth.dto.response.SignUpOwnerResponseDto;
import com.example.outsourcingproject.auth.repository.OwnerAuthRepository;
import com.example.outsourcingproject.entity.Owner;
import com.example.outsourcingproject.exception.CustomException;
import com.example.outsourcingproject.exception.ErrorCode;
import com.example.outsourcingproject.utils.JwtUtil;
import com.example.outsourcingproject.utils.PasswordEncoder;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OwnerAuthServiceImpl implements OwnerAuthService{

    private final OwnerAuthRepository ownerAuthRepository;
    private final JwtUtil jwtUtil;
    PasswordEncoder bcrypt = new PasswordEncoder();

    public OwnerAuthServiceImpl(OwnerAuthRepository ownerAuthRepository, JwtUtil jwtUtil) {
        this.ownerAuthRepository = ownerAuthRepository;
        this.jwtUtil = jwtUtil;
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
    public SignInOwnerResponseDto signIn(String email, String rawPassword) {
        // todo 로그인 상태가 아닌 사장만 들어올 수 있게 -> 필터

        // 탈퇴하지 않은 사장님들 중에서 이메일 값이 일치하는 사장님 추출
        Owner owner = ownerAuthRepository.findByEmailAndIsDeleted(email, 0)
            .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        String encodedPassword = owner.getPassword();

        boolean isPasswordMisMatching = !bcrypt.matches(rawPassword, encodedPassword);

        if(isPasswordMisMatching){
            log.info("아이디 또는 비밀번호가 잘못되었습니다.");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        log.info("사장님 {} 로그인 완료", email);

        String token = jwtUtil.createToken(email, owner.getAuthority());

        return new SignInOwnerResponseDto(token);
    }

    @Override
    public void deleteOwner(String rawPassword, String token) {
        // jwt 토큰에 저장된 손님 이메일 추출
        String ownerEmail = jwtUtil.extractCustomerEmail(token);

        // 추출한 이메일로 손님 조회
        Owner owner = ownerAuthRepository.findByEmail(ownerEmail)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CUSTOMER));

        // 비밀번호 일치 여부 확인
        String encodedPassword = owner.getPassword();
        boolean isPasswordMisMatching = !bcrypt.matches(rawPassword, encodedPassword);

        if(isPasswordMisMatching){
            log.info("아이디 또는 비밀번호가 잘못되었습니다.");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        log.info("손님 {} 탈퇴 완료", ownerEmail);

        // 회원 삭제
        ownerAuthRepository.updateIsDeletedByEmail(ownerEmail, 1);
        LocalDateTime currentTime = LocalDateTime.now();
        ownerAuthRepository.updateDeletedAtByEmail(ownerEmail, currentTime);

        // todo 토큰 삭제 (무효화) 해야함.. 지금은 탈퇴시 데이터만 지우는 걸로
    }


}
