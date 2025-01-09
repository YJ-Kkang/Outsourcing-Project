package com.example.outsourcingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stores")
@Getter
@Setter
@NoArgsConstructor
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeTelephone;

    private String storeAddress;
    private BigInteger minimumPurchase;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private LocalDateTime deletedAt;


    public StoreEntity(
        Long ownerId,
        String storeName,
        String storeTelephone,
        String storeAddress,
        BigInteger minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean isDeleted,
        LocalDateTime deletedAt) {

        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeTelephone = storeTelephone;
        this.storeAddress = storeAddress;
        this.minimumPurchase = minimumPurchase;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}