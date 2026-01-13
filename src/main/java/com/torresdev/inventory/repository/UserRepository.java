package com.torresdev.inventory.repository;

import com.torresdev.inventory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsernameAndActiveTrue(String username);
}
