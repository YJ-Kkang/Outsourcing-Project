package com.example.outsourcingproject.category.dto.response;

import com.example.outsourcingproject.entity.Category;
import lombok.Getter;

@Getter
public class CreateCategoryResponseDto {

    private final Long id;
    private final String name;

    public CreateCategoryResponseDto(
        Category category
    ) {
        this.id = category.getId();
        this.name = category.getName();
    }
}