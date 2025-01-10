package com.example.outsourcingproject.review.repository;

import com.example.outsourcingproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
