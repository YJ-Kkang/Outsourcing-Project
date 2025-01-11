package com.example.outsourcingproject.store.dto.response;

import com.example.outsourcingproject.entity.Category;
import com.example.outsourcingproject.entity.Store;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class CreateStoreResponseDto {

    private final Long id;
    private final String storeName;
    private final String storeAddress;
    private final String storeTelephone;
    private final Integer minimumPurchase;
    private final LocalTime opensAt;
    private final LocalTime closesAt;
    private final Category categoryOne;
    private final Category categoryTwo;

    public CreateStoreResponseDto(Store store) {
        this.id = store.getId();
        this.storeName = store.getStoreName();
        this.storeAddress = store.getStoreAddress();
        this.storeTelephone = store.getStoreTelephone();
        this.minimumPurchase = store.getMinimumPurchase();
        this.opensAt = store.getOpensAt();
        this.closesAt = store.getClosesAt();
        this.categoryOne = store.getCategoryOne();
        this.categoryTwo = store.getCategoryTwo();
    }
}