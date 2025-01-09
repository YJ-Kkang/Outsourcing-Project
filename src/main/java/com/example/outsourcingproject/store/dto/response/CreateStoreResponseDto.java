package com.example.outsourcingproject.store.dto.response;

import java.math.BigInteger;
import java.time.LocalTime;
import lombok.Getter;

@Getter
public class CreateStoreResponseDto {

  private Long id;
  private final String storeName;
  private final String storeAddress;
  private final String storeTelephone;
  private final BigInteger minimumPurchase;
  private final LocalTime opensAt;
  private final LocalTime closesAt;

  public CreateStoreResponseDto(
      Long id,
      String storeName,
      String storeAddress,
      String storeTelephone,
      BigInteger minimumPurchase,
      LocalTime opensAt,
      LocalTime closesAt
  ) {
    this.id = id;
    this.storeName = storeName;
    this.storeAddress = storeAddress;
    this.storeTelephone = storeTelephone;
    this.minimumPurchase = minimumPurchase;
    this.opensAt = opensAt;
    this.closesAt = closesAt;
  }
}
