package com.torresdev.inventory.repository;

import com.torresdev.inventory.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Optional<Profile> findByUsernameAndActiveTrue(String username);
}
