package com.torresdev.inventory.dto.product;

import java.util.UUID;

public class ProductWithStockResponseDTO {

    private UUID id;
    private String name;
    private int currentStock;
    private Integer minimumStock;
    private boolean active;

    public ProductWithStockResponseDTO(
            UUID id,
            String name,
            int currentStock,
            Integer minimumStock,
            boolean active
    ) {
        this.id = id;
        this.name = name;
        this.currentStock = currentStock;
        this.minimumStock = minimumStock;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }

    public boolean isActive() {
        return active;
    }
}
