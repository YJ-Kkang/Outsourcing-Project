package com.example.outsourcingproject.store.dto.request;

import java.time.LocalTime;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateStoreRequestDto {

    private final String storeName;
    private final String storeAddress;
    private final String storeTelephone;
    private final Integer minimumPurchase;
    private final LocalTime opensAt;
    private final LocalTime closesAt;
    private final List<String> storeCategoryNameList;

    public CreateStoreRequestDto(
        String storeName,
        String storeAddress,
        String storeTelephone,
        Integer minimumPurchase,
        LocalTime opensAt,
        LocalTime closesAt,
        List<String> storeCategoryNameList
    ) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeTelephone = storeTelephone;
        this.minimumPurchase = minimumPurchase;
        this.opensAt = opensAt;
        this.closesAt = closesAt;
        this.storeCategoryNameList = storeCategoryNameList;
    }
}
