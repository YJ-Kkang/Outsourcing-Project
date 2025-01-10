package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignInCustomerResponseDto;
import com.example.outsourcingproject.auth.dto.response.SignUpCustomerResponseDto;
import com.example.outsourcingproject.auth.repository.CustomerAuthRepository;
import com.example.outsourcingproject.entity.Customer;
import com.example.outsourcingproject.exception.CustomException;
import com.example.outsourcingproject.exception.ErrorCode;
import com.example.outsourcingproject.utils.JwtUtil;
import com.example.outsourcingproject.utils.PasswordEncoder;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerAuthServiceImpl implements CustomerAuthService{

    private final CustomerAuthRepository customerAuthRepository;
    private final JwtUtil jwtUtil;
//    private final TokenBlacklistService tokenBlacklistService;
    PasswordEncoder bcrypt = new PasswordEncoder();


    @Override
    public SignUpCustomerResponseDto signUp(String email, String password) {

        // 등록된 아이디(이메일) 여부 확인
        boolean isExistEmail = customerAuthRepository.findByEmail(email).isPresent();

        if(isExistEmail) {
            log.info("이미 존재하는 이메일입니다. >> {}", email);
            throw new CustomException(ErrorCode.EMAIL_EXIST);
        }
        Customer customer = new Customer(email, bcrypt.encode(password));
        Customer savedCustomer = customerAuthRepository.save(customer);

        log.info("손님 {} 회원가입 완료", email);
        return new SignUpCustomerResponseDto(savedCustomer);
    }

    @Override
    public SignInCustomerResponseDto signIn(String email, String rawPassword) {
        // todo 로그인 상태가 아닌 손님만 들어올 수 있게 -> 필터

        // 탈퇴하지 않은 손님들 중에서 이메일 값이 일치하는 손님 추출
        Customer customer = customerAuthRepository.findByEmailAndIsDeleted(email, 0)
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        String encodedPassword = customer.getPassword();

        boolean isPasswordMisMatching = !bcrypt.matches(rawPassword, encodedPassword);

        if(isPasswordMisMatching){
            log.info("아이디 또는 비밀번호가 잘못되었습니다.");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        log.info("손님 {} 로그인 완료", email);

        String token = jwtUtil.createToken(email, customer.getAuthority());

        return new SignInCustomerResponseDto(token);
    }

    @Override
    @Transactional
    public void deleteCustomer(String rawPassword, String token) {

        // jwt 토큰에 저장된 손님 이메일 추출
        String customerEmail = jwtUtil.extractCustomerEmail(token);

        // 추출한 이메일로 손님 조회
        Customer customer = customerAuthRepository.findByEmail(customerEmail)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CUSTOMER));

        // 비밀번호 일치 여부 확인
        String encodedPassword = customer.getPassword();
        boolean isPasswordMisMatching = !bcrypt.matches(rawPassword, encodedPassword);

        if(isPasswordMisMatching){
            log.info("아이디 또는 비밀번호가 잘못되었습니다.");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        log.info("손님 {} 탈퇴 완료", customerEmail);

        // 회원 삭제
        customerAuthRepository.updateIsDeletedByEmail(customerEmail, 1);
        LocalDateTime currentTime = LocalDateTime.now();
        customerAuthRepository.updateDeletedAtByEmail(customerEmail, currentTime);

        // todo 토큰 삭제 (무효화) 해야함.. 지금은 탈퇴시 데이터만 지우는 걸로

    }
}
