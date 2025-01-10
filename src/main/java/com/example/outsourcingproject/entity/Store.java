package com.example.outsourcingproject.entity;

import com.example.outsourcingproject.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stores")
@Getter
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ownerId;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeTelephone;

    //todo @Column 해주기..
    private String storeAddress;
    private Integer minimumPurchase;
    private LocalTime opensAt;
    private LocalTime closesAt;
    private Integer isDeleted;
    private LocalDateTime deletedAt;

    public Store() {
    }

    public Store(
        Long ownerId,
        String storeName,
        String storeTelephone,
        String storeAddress,
        Integer minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt) {

        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeTelephone = storeTelephone;
        this.storeAddress = storeAddress;
        this.minimumPurchase = minimumPurchase;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.isDeleted = 0;
        this.deletedAt = null;
    }

}