package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignUpCustomersResponseDto;
import com.example.outsourcingproject.auth.repository.CustomerAuthRepository;
import com.example.outsourcingproject.config.PasswordEncoder;
import com.example.outsourcingproject.config.error.CustomException;
import com.example.outsourcingproject.config.error.ErrorCode;
import com.example.outsourcingproject.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerAuthServiceImpl implements CustomerAuthService{

    private final CustomerAuthRepository customerAuthRepository;
    PasswordEncoder bcrypt = new PasswordEncoder();

    public CustomerAuthServiceImpl(CustomerAuthRepository customerAuthRepository) {
        this.customerAuthRepository = customerAuthRepository;
    }

    @Override
    public SignUpCustomersResponseDto signUp(String email, String password) {

        // (1) 등록된 아이디(이메일) 여부 확인

        boolean isExistEmail = customerAuthRepository.existsByEmail(email);

        if(isExistEmail) {
            log.info("이미 가입된 이메일입니다. >> {}", email);

            // 임시 예외처리
            throw new RuntimeException("이미 가입된 이메일입니다.");
            // todo 예외처리핸들러
        }
        Customer customer = new Customer(email, bcrypt.encode(password));
        Customer savedCustomer = customerAuthRepository.save(customer);

        return new SignUpCustomersResponseDto(savedCustomer);
    }

    @Override
    public void signIn(String email, String rawPassword) {
        // todo 반환

        Customer customer = customerAuthRepository.findByEmail(email).
            orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        String encodedPassword = customer.getPassword();

        boolean isPasswordMisMatching = !bcrypt.matches(rawPassword, encodedPassword);

        if(isPasswordMisMatching){
            log.info("아이디 또는 비밀번호가 잘못되었습니다.");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        log.info("손님 로그인 성공 >>> {}", email);
        // todo return 손님 토큰
    }
}
