package com.example.outsourcingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "stores")
@Getter
public class Store extends BaseEntity {

    @Comment("가게 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("사장님 식별자")
    private Long ownerId;

    @Comment("가게 이름")
    @Column(
        name = "store_name",
        nullable = false)
    private String storeName;

    @Comment("가게 전화번호")
    @Column(
        name = "store_telephone",
        nullable = false)
    private String storeTelephone;

    @Comment("가게 주소")
    @Column(
        name = "store_address",
        nullable = false
    )
    private String storeAddress;


    @Comment("주문 최소 금액")
    @Column(
        name = "minimum_purchase",
        nullable = false
    )
    private Integer minimumPurchase;

    @Comment("여는 시간")
    @Column(
        name = "opens_at",
        nullable = false
    )
    private LocalTime opensAt;

    @Comment("닫는 시간")
    @Column(
        name = "closes_at",
        nullable = false
    )
    private LocalTime closesAt;

    @Comment("첫 번째 메뉴 카테고리 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_one_id"
    )
    private Category categoryOne;

    @Comment("두 번째 메뉴 카테고리 식별자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "category_two_id"
    )
    private Category categoryTwo;

    @Column(
        insertable=false, updatable=false
    )
    private final boolean is_deleted = Boolean.FALSE;

    protected Store() {
    }

    public Store(
        Long ownerId,
        String storeName,
        String storeAddress,
        String storeTelephone,
        Integer minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt,
        Category categoryOne,
        Category categoryTwo
    ) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeTelephone = storeTelephone;
        this.storeAddress = storeAddress;
        this.minimumPurchase = minimumPurchase;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.categoryOne = categoryOne;
        this.categoryTwo = categoryTwo;
    }

    public void update(
        String storeName,
        String storeAddress,
        String storeTelephone,
        Integer minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt
    ) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeTelephone = storeTelephone;
        this.minimumPurchase = minimumPurchase;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
    }

}