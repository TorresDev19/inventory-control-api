package com.torresdev.inventory.dto.stock;

import com.torresdev.inventory.entity.MovementType;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StockMovementReportDTO {

    private UUID productId;
    private MovementType type;
    private Integer quantity;
    private OffsetDateTime createdAt;

    public StockMovementReportDTO(
            UUID productId,
            MovementType type,
            Integer quantity,
            OffsetDateTime createdAt
    ) {
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public UUID getProductId() {
        return productId;
    }

    public MovementType getMovementType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
