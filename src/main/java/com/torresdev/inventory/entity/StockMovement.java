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

    // 🔒 Construtor padrão exigido pelo JPA
    protected StockMovement() {
    }

    // ✅ ESTE é o construtor que o service está usando
    public StockMovement(
            UUID productId,
            Integer quantity,
            MovementType movementType,
            OffsetDateTime createdAt
    ) {
        this.productId = productId;
        this.quantity = quantity;
        this.movementType = movementType;
        this.createdAt = createdAt;
    }

    // Getters (JPA precisa deles)
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
}
