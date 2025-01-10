package com.example.outsourcingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 주문 생성일
    // 다른 생성일과 함께 쓸 수 있도록 '생성일'이라고 지음
    @Comment("생성일")
    @CreatedDate
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(
        name = "created_at",
        nullable = false,
        updatable = false,
        columnDefinition = "TIMESTAMP"
    )
    private LocalDateTime createdAt;

    // 주문 상태 변경일
    // 다른 생성일과 함께 쓸 수 있도록 '생성일'이라고 지음
    @Comment("수정일")
    @LastModifiedDate
    @Column(
        name = "updated_at",
        nullable = false,
        columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    private LocalDateTime updatedAt;

    protected BaseEntity() {
    }
}