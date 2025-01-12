package com.example.outsourcingproject.category.repository;

import com.example.outsourcingproject.entity.StoreCategory;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

    List<StoreCategory> findAllByNameIn(Collection<String> names, Sort sort);
}
