package com.example.outsourcingproject.auth.repository;

import com.example.outsourcingproject.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAuthRepository extends JpaRepository<Customer, Long> {

    // 이메일 중복 여부 검증 : email 컬럼에 손님이 입력한 email 이 있으면 true , 없으면 false
    boolean existsByEmail(String email);

    Optional<Customer> findByEmail(String email);
}
