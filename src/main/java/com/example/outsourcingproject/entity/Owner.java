package com.example.outsourcingproject.entity;

import com.example.outsourcingproject.common.Authority;
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
//@SoftDelete //todo
@Table(name = "owners")
public class Owner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Customer 테이블에 저장된 데이터의 authority는 무조건 사장님
    // 손님으로 권한 수정? -> 사장님 데이터 삭제(탈퇴) 후 손님 테이블에 데이터 생성(회원가입) 하기
    @Enumerated(EnumType.STRING)
    @Column(updatable = false, nullable = false)
    private Authority authority;

    // todo @softdelete 어노테이션을 쓸거면 지우기
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Integer isDeleted;

    @Column
    private LocalDateTime deletedAt;

    public Owner() {
    }

    public Owner(
        String email,
        String password
    ) {
        this.email = email;
        this.password = password;
        this.authority = Authority.OWNER;
        this.isDeleted = 0;
        this.deletedAt = null;
    }
}
