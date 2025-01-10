package com.example.outsourcingproject.entity;

import com.example.outsourcingproject.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
public class Store extends BaseEntity {

    @Comment("가게 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private String storeName;

    @Column(nullable = false)
    private String storeTelephone;

    //todo @Column 해주기..
    private String storeAddress;
    private Integer minimumPurchase;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer isDeleted;
    private LocalDateTime deletedAt;


    public Store(
        String storeName,
        String storeTelephone,
        String storeAddress,
        Integer minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt
    ) {

//        this.storeName = storeName;
        this.storeTelephone = storeTelephone;
        this.storeAddress = storeAddress;
        this.minimumPurchase = minimumPurchase;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.isDeleted = 0;
        this.deletedAt = null;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = 0;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}