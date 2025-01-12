package com.example.outsourcingproject.category.dto.response;

import com.example.outsourcingproject.entity.StoreCategory;
import lombok.Getter;

@Getter
public class CreateStoreCategoryResponseDto {

    private final Long id;
    private final String name;

    public CreateStoreCategoryResponseDto(
        StoreCategory storeCategory
    ) {
        this.id = storeCategory.getId();
        this.name = storeCategory.getName();
    }
}