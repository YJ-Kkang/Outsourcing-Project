package com.example.outsourcingproject.auth.repository;

import com.example.outsourcingproject.entity.Owner;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerAuthRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByEmail(String email);

}
