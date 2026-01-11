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

    @Column(name = "minimum_stock", nullable = false)
    private Integer minimumStock;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    protected Product() {
    }

    public Product(String name, Integer minimumStock) {
        this.name = name;
        this.minimumStock = minimumStock;
        this.active = true;
        this.createdAt = OffsetDateTime.now();
    }

    // GETTERS
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
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

    // DOMAIN BEHAVIOR
    public void update(String name, Integer minimumStock) {
        this.name = name;
        this.minimumStock = minimumStock;
    }

    public void deactivate() {
        this.active = false;
    }
}
