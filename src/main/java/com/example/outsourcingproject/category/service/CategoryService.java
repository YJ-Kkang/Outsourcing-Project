package com.example.outsourcingproject.category.service;

import com.example.outsourcingproject.category.dto.request.CreateCategoryRequestDto;
import com.example.outsourcingproject.category.dto.response.CreateCategoryResponseDto;
import com.example.outsourcingproject.category.repository.CategoryRepository;
import com.example.outsourcingproject.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CreateCategoryResponseDto createCategory(
        CreateCategoryRequestDto requestDto
    ) {
        Category categoryToSave = new Category(requestDto.getName());

        Category savedCategory = categoryRepository.save(categoryToSave);

        return new CreateCategoryResponseDto(savedCategory);

    }

}
