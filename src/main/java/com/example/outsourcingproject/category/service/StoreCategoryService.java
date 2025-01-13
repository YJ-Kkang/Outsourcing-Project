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
    public CreateStoreCategoryResponseDto createCategory(CreateStoreCategoryRequestDto requestDto) {
        // StoreCategory 엔티티 생성
        StoreCategory storeCategoryToSave = new StoreCategory(requestDto.getName());
        // 엔티티 저장
        StoreCategory savedStoreCategory = storeCategoryRepository.save(storeCategoryToSave);
        // 응답 DTO 생성: savedStoreCategory에서 ID와 이름을 가져와 응답 DTO 생성
        return new CreateStoreCategoryResponseDto(savedStoreCategory.getId(), savedStoreCategory.getName());
    }
}