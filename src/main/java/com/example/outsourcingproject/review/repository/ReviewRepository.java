package com.example.outsourcingproject.review.repository;

import com.example.outsourcingproject.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 별점 범위로 리뷰 조회
    List<Review> findByStoreIdAndRatingBetween(Long storeId, Integer startRating, Integer endRating);
}