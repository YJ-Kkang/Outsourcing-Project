package com.example.outsourcingproject.store.repository;

import com.example.outsourcingproject.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 사장님이 보유한 가게 수를 조회하는 기능
    Long countByOwnerId(Long ownerId);

    // 가게 이름으로 가게를 조회할 때 폐업하지 않은 가게만 조회하는 기능
    List<Store> findByStoreNameContainingAndIsDeletedFalse(String storeName);
}