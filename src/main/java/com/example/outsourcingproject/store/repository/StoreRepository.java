package com.example.outsourcingproject.store.repository;

import com.example.outsourcingproject.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
}

