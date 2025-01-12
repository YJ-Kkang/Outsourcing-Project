package com.example.outsourcingproject.store.dto.response;

import com.example.outsourcingproject.entity.Store;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class StoreCategorySearchResponseDto {

    private final String storeName;
    private final String storeAddress;
    private final String storeTelephone;
    private final Integer minimumPurchase;
    private final LocalTime opensAt;
    private final LocalTime closesAt;

    public StoreCategorySearchResponseDto(Store store) {
        this.storeName = store.getStoreName();
        this.storeAddress = store.getStoreAddress();
        this.storeTelephone = store.getStoreTelephone();
        this.minimumPurchase = store.getMinimumPurchase();
        this.opensAt = store.getOpensAt();
        this.closesAt = store.getClosesAt();
    }
}