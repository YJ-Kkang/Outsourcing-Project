package com.example.outsourcingproject.category.dto.request;

import lombok.Getter;

@Getter
public class CreateStoreCategoryRequestDto {

    private final String name;

    public CreateStoreCategoryRequestDto(String name) {
        this.name = name;

    }
}
