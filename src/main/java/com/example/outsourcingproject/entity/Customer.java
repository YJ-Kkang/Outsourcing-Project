package com.example.outsourcingproject.entity;

import com.example.outsourcingproject.common.Authority;
import com.example.outsourcingproject.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Entity
//@SoftDelete //todo 일단 어노테이션으로 실험
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Customer 테이블에 저장된 데이터의 authority는 무조건 손님
    // 사장으로 권한 수정? -> 손님 데이터 삭제 후 사장님 테이블에 데이터 생성
    @Enumerated(EnumType.STRING)
    @Column(updatable = false, nullable = false)
    private Authority authority;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Integer isDeleted;

    @Column
    private LocalDateTime deleted_at;

    public Customer() {
    }

    public Customer(
        String email, String password) {
        this.email = email;
        this.password = password;
        this.authority = Authority.CUSTOMER;
        this.isDeleted = 0;
        this.deleted_at = null;
    }
}
