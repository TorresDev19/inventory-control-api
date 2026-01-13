package com.torresdev.inventory.dto.stock;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StockMovementResponseDTO {

    private UUID id;
    private UUID productId;
    private String type;
    private Integer quantity;
    private OffsetDateTime createdAt;

    public StockMovementResponseDTO(
            UUID id,
            UUID productId,
            String type,
            Integer quantity,
            OffsetDateTime createdAt
    ) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
