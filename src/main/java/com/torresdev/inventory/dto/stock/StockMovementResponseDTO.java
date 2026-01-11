package com.torresdev.inventory.dto.stock;

import com.torresdev.inventory.entity.MovementType;

import java.time.OffsetDateTime;

public class StockMovementResponseDTO {

    private MovementType type;
    private Integer quantity;
    private OffsetDateTime createdAt;

    public StockMovementResponseDTO(
            MovementType type,
            Integer quantity,
            OffsetDateTime createdAt
    ) {
        this.type = type;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public MovementType getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
