package com.example.outsourcingproject.auth.repository;

import com.example.outsourcingproject.entity.Owner;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OwnerAuthRepository extends JpaRepository<Owner, Long> {

    // 이메일로 전체 사장님 조회
    Optional<Owner> findByEmail(String email);

    // 탈퇴한 사장님은 제외하고 이메일이 일치하는 데이터 조회
    Optional<Owner> findByEmailAndIsDeletedFalse(String email);

    // Email을 기준으로 isDeleted 값을 업데이트
    @Modifying
    @Transactional
    @Query("UPDATE Owner c SET c.isDeleted = :isDeleted WHERE c.email = :email")
    void updateIsDeletedByEmail(@Param("email") String email, @Param("isDeleted") Integer isDeleted);

    // Email을 기준으로 deletedAt 값을 업데이트
    @Modifying
    @Transactional
    @Query("UPDATE Owner c SET c.deletedAt = :deletedAt WHERE c.email = :email")
    void updateDeletedAtByEmail(@Param("email") String email, @Param("deletedAt") LocalDateTime deletedAt);

}
