package com.example.outsourcingproject.auth.service;

import com.example.outsourcingproject.auth.dto.response.SignUpCustomersResponseDto;
import com.example.outsourcingproject.auth.repository.CustomerAuthRepository;
import com.example.outsourcingproject.config.PasswordEncoder;
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
}
