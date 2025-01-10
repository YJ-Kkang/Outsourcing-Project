package com.example.outsourcingproject.store.dto.response;


import java.time.LocalTime;
import lombok.Getter;

@Getter
public class StoreNameResponseDto {

    private final String storeName;
    private final String storeAddress;
    private final String storeTelephone;
    private final Integer minimumPurchase;
    private final LocalTime opensAt;
    private final LocalTime closesAt;

    public StoreNameResponseDto(
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
