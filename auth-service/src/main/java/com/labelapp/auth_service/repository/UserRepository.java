package com.labelapp.auth_service.repository;

import com.labelapp.auth_service.enums.Role;
import com.labelapp.auth_service.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndRole(String email, Role role);

    Page<User> findByEmailContains(String email, Pageable pageable);

    Page<User> findAllByEmail(String email, Pageable pageable);

    Boolean existsByEmail(String email);
}
