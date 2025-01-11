package com.example.outsourcingproject.review.repository;

import com.example.outsourcingproject.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByStoreId(Long id);

    // 최신순 반환
//    List<Review> findByStoreIdOrderBy

    // 평점 기준 반환
}