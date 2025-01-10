package com.example.outsourcingproject.store.repository;

import com.example.outsourcingproject.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    List<Store> findByStoreNameContaining(String storeName);
}

