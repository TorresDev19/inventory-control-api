package com.torresdev.inventory.dto.user;

import java.time.OffsetDateTime;
import java.util.UUID;

public class UserResponseDTO {

    private UUID id;
    private String username;
    private String role;
    private OffsetDateTime createdAt;

    public UserResponseDTO() {
    }

    public UserResponseDTO(UUID id, String username, String role, OffsetDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
