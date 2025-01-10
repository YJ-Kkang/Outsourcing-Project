package com.example.outsourcingproject.entity;

import com.example.outsourcingproject.order.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order extends BaseEntity {

    @Comment("주문 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("주문 상태")
    @Enumerated(EnumType.STRING)
    @Column(
        name = "order_status",
        nullable = false
    )
    private OrderStatus orderStatus;

    @Comment("주문 총 수량")
    @Column(
        name = "total_amount_sum",
        nullable = false
    )
    private Integer totalAmountSum = 0;

    @Comment("주문 총가격")
    @Column(
        name = "total_price_sum",
        nullable = false
    )
    private Integer totalPriceSum = 0;

    @Comment("가게")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "store_id",
        nullable = false
    )
    private Store store;

    protected Order() {
    }

    public Order(
        OrderStatus orderStatus,
        Store store
    ) {
        this.orderStatus = orderStatus;
        this.store = store;
    }

    public void updateTotals(
        Integer totalAmountSum,
        Integer totalPriceSum
    ) {
        this.totalAmountSum = totalAmountSum;
        this.totalPriceSum = totalPriceSum;
    }

    // 주문 상태를 변경하는 기능
    public void updateOrderStatus(OrderStatus orderStatus) {
        validateStatusSequence(orderStatus);
        this.orderStatus = orderStatus;
    }

    // 주문 상태 변경 순서가 올바른지 검증하는 기능
    private void validateStatusSequence(OrderStatus orderStatus) {

        boolean isInvalidPendingTransition = !(orderStatus.equals(OrderStatus.ACCEPTED)
            || orderStatus.equals(OrderStatus.CANCELED));

        boolean isInvalidAcceptedTransition = !orderStatus.equals(OrderStatus.DELIVERING);

        boolean isInvalidDeliveringTransition = !orderStatus.equals(OrderStatus.DELIVERED);

        // !accepted이면 예외 던지기

        // 끝난다는 걸 명확히 하고자 예외
        switch (this.orderStatus) {
            case PENDING:
                if (isInvalidPendingTransition) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                } // todo
                break;
            case ACCEPTED:
                if (isInvalidAcceptedTransition) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                } // todo
                break;
            case DELIVERING:
                if (isInvalidDeliveringTransition) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT);
                } // todo
                break;
        }
    }
}