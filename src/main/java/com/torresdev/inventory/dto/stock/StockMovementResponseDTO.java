package com.torresdev.inventory.dto.stock;

import java.time.OffsetDateTime;
import java.util.UUID;

public class StockMovementResponseDTO {

    private UUID id;
    private UUID productId;
    private String type;
    private Integer quantity;
    private OffsetDateTime createdAt;
    private String observacao;

    public StockMovementResponseDTO(
            UUID id,
            UUID productId,
            String type,
            Integer quantity,
            OffsetDateTime createdAt,
            String observacao) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }
}
