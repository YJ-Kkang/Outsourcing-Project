package com.example.outsourcingproject.auth.repository;

import com.example.outsourcingproject.entity.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAuthRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);
}
