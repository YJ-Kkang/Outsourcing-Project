package com.example.outsourcingproject.category.service;

import com.example.outsourcingproject.category.dto.request.CreateStoreCategoryRequestDto;
import com.example.outsourcingproject.category.dto.response.CreateStoreCategoryResponseDto;
import com.example.outsourcingproject.category.repository.StoreCategoryRepository;
import com.example.outsourcingproject.entity.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    @Transactional
    public CreateStoreCategoryResponseDto createCategory(
        CreateStoreCategoryRequestDto requestDto
    ) {
        StoreCategory storeCategoryToSave = new StoreCategory(requestDto.getName());

        StoreCategory savedStoreCategory = storeCategoryRepository.save(storeCategoryToSave);

        return new CreateStoreCategoryResponseDto(savedStoreCategory);

    }

}
