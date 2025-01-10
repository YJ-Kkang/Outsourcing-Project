package com.example.outsourcingproject.store.dto.response;


import java.time.LocalTime;
import lombok.Getter;

@Getter
public class StoreResponseDto {

    private Long id;
    private final String storeName;
    private final String storeAddress;
    private final String storeTelephone;
    private final Integer minimumPurchase;
    private final LocalTime opensAt;
    private final LocalTime closesAt;

    public StoreResponseDto(
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

//    public static StoreResponseDto convertDto(StoreEntity store) {
//        return new StoreResponseDto(
//            store.getId(),
//            store.getStoreName(),
//            store.getStoreAddress(),
//            store.getStoreTelephone(),
//            store.getMinimumPurchase(),
//            store.getOpensAt(),
//            store.getClosesAt());
//    }
}
