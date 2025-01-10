package com.example.outsourcingproject.store.repository;

import com.example.outsourcingproject.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

//    List<StoreEntity> findByStoreNameContaing(String storeName);
}

