package com.torresdev.inventory.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "stock_movements")
public class StockMovement {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private MovementType movementType;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "observacao")
    private String observacao;

    protected StockMovement() {
    }

    public StockMovement(
            UUID productId,
            Integer quantity,
            MovementType movementType,
            OffsetDateTime createdAt,
            String observacao) {
        this.productId = productId;
        this.quantity = quantity;
        this.movementType = movementType;
        this.createdAt = createdAt;
        this.observacao = observacao;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getObservacao() {
        return observacao;
    }

    public boolean isEntry() {
        return movementType == MovementType.ENTRY;
    }

    public boolean isExit() {
        return movementType == MovementType.EXIT;
    }
}
