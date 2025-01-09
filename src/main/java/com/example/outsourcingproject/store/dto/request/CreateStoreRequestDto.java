package com.example.outsourcingproject.store.dto.request;

import java.math.BigInteger;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class CreateStoreRequestDto {

  private final String storeName;
  private final String storeAddress;
  private final String storeTelephone;
  private final BigInteger minimumPurchase;
  private final LocalTime opensAt;
  private final LocalTime closesAt;

  public CreateStoreRequestDto(
      String storeName,
      String storeAddress,
      String storeTelephone,
      BigInteger minimumPurchase,
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
