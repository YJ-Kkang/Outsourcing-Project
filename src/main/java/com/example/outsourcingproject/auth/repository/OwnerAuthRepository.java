package com.example.outsourcingproject.auth.repository;

import com.example.outsourcingproject.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerAuthRepository extends JpaRepository<Owner, Long> {

    // 이메일 중복 여부 검증 : email 컬럼에 사장님이 입력한 email 이 있으면 true , 없으면 false
    boolean existsByEmail(String email);

}
