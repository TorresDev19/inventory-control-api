package com.torresdev.inventory.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private UUID tenantId;

    @Column(nullable = false)
    private boolean active = true;

    protected User() {
    }

    public User(String username, String passwordHash, UUID tenantId) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.tenantId = tenantId;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public boolean isActive() {
        return active;
    }
}
