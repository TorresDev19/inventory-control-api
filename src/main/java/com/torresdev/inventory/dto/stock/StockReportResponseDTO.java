package com.torresdev.inventory.dto.stock;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StockReportResponseDTO {

    private UUID productId;
    private String name;
    private int minimumStock;
    private int currentStock;
    private String status;
    private OffsetDateTime createdAt;

    public StockReportResponseDTO(
            UUID productId,
            String name,
            int minimumStock,
            int currentStock,
            String status,
            OffsetDateTime createdAt
    ) {
        this.productId = productId;
        this.name = name;
        this.minimumStock = minimumStock;
        this.currentStock = currentStock;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getMinimumStock() {
        return minimumStock;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public String getStatus() {
        return status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
