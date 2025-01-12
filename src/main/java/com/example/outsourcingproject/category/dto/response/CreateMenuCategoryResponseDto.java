package com.example.outsourcingproject.category.dto.response;

import com.example.outsourcingproject.entity.MenuCategory;
import lombok.Getter;

@Getter
public class CreateMenuCategoryResponseDto {

    private final Long id;
    private final String name;

    public CreateMenuCategoryResponseDto(MenuCategory menuCategory) {
        this.id = menuCategory.getId();
        this.name = menuCategory.getName();
    }
}
