package com.example.skillpath_pro.repository;

import com.example.skillpath_pro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmationToken(String confirmationToken);
}
