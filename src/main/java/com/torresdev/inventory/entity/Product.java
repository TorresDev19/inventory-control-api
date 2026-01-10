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

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "minimum_stock", nullable = false)
    private Integer minimumStock;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    // JPA only
    protected Product() {
    }

    // Constructor usado pelo Service
    public Product(String name, String description, Integer minimumStock) {
        this.name = name;
        this.description = description;
        this.minimumStock = minimumStock;
        this.active = true;
        this.createdAt = OffsetDateTime.now();
    }

    // ===== Getters =====

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

    // ===== Domain behavior =====

    public void update(String name, String description, Integer minimumStock) {
        this.name = name;
        this.description = description;
        this.minimumStock = minimumStock;
    }

    public void deactivate() {
        this.active = false;
    }
}
