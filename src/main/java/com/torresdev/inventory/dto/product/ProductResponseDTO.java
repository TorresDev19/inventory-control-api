package com.torresdev.inventory.dto.product;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductResponseDTO {

    private UUID id;
    private String name;
    private String description;
    private Integer minimumStock;
    private boolean active;
    private OffsetDateTime createdAt;

    public ProductResponseDTO(
            UUID id,
            String name,
            String description,
            Integer minimumStock,
            boolean active,
            OffsetDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minimumStock = minimumStock;
        this.active = active;
        this.createdAt = createdAt;
    }

    // ========= GETTERS =========

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
