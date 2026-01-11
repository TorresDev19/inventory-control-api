package com.torresdev.inventory.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer minimumStock;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    protected Product() {
        // JPA
    }

    public Product(String name, String description, Integer minimumStock) {
        this.name = name;
        this.description = description;
        this.minimumStock = minimumStock;
    }

    // =========================
    // DOMAIN METHODS
    // =========================
    public void update(String name, String description, Integer minimumStock) {
        this.name = name;
        this.description = description;
        this.minimumStock = minimumStock;
    }

    public void deactivate() {
        this.active = false;
    }

    // =========================
    // GETTERS
    // =========================
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
