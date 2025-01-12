package com.example.outsourcingproject.category.service;

import com.example.outsourcingproject.category.dto.request.CreateMenuCategoryRequestDto;
import com.example.outsourcingproject.category.dto.response.CreateMenuCategoryResponseDto;
import com.example.outsourcingproject.category.repository.MenuCategoryRepository;
import com.example.outsourcingproject.entity.MenuCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuCategoryService {

    private final MenuCategoryRepository menuCategoryRepository;

    @Transactional
    public CreateMenuCategoryResponseDto createMenuCategory(
        CreateMenuCategoryRequestDto requestDto
    ) {
        MenuCategory menuCategoryToSave = new MenuCategory(requestDto.getName());

        MenuCategory savedMenuCategory = menuCategoryRepository.save(menuCategoryToSave);

        return new CreateMenuCategoryResponseDto(savedMenuCategory);

    }
}
