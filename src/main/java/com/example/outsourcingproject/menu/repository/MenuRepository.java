package com.example.outsourcingproject.menu.repository;

import com.example.outsourcingproject.entity.Menu;
import com.example.outsourcingproject.entity.Store;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByStoreId(Long storeId);

    List<Menu> findAllByStoreIdAndIsDeleted(Long storeId, Integer isDeleted);

}
