package com.torresdev.inventory.dto.product;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductWithStockResponseDTO {

    private final UUID id;
    private final String name;
    private final String description;
    private final Integer minimumStock;
    private final Integer currentStock;
    private final boolean active;
    private final OffsetDateTime createdAt;

    public ProductWithStockResponseDTO(
            UUID id,
            String name,
            String description,
            Integer minimumStock,
            Integer currentStock,
            boolean active,
            OffsetDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.minimumStock = minimumStock;
        this.currentStock = currentStock;
        this.active = active;
        this.createdAt = createdAt;
    }

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

    public Integer getCurrentStock() {
        return currentStock;
    }

    public boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
