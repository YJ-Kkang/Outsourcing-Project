package com.example.outsourcingproject.menu.repository;

import com.example.outsourcingproject.entity.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByStoreId(Long storeId);

    List<Menu> findAllByStoreIdAndIsDeleted(Long storeId, Integer isDeleted);

}
