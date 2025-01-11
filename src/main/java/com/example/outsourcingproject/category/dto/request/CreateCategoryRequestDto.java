package com.example.outsourcingproject.category.dto.request;

import lombok.Getter;

@Getter
public class CreateCategoryRequestDto {

    private final String name;

    public CreateCategoryRequestDto(String name) {
        this.name = name;

    }
}
