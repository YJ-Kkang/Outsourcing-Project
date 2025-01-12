package com.example.outsourcingproject.category.repository;

import com.example.outsourcingproject.entity.MenuCategory;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {

    List<MenuCategory> findAllByNameIn(Collection<String> names, Sort sort);
}
