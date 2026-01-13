package com.torresdev.inventory.repository;

import com.torresdev.inventory.entity.MovementType;
import com.torresdev.inventory.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

    List<StockMovement> findByProductIdOrderByCreatedAtDesc(UUID productId);

    List<StockMovement> findByProductIdAndMovementTypeOrderByCreatedAtDesc(
            UUID productId,
            MovementType movementType
    );

    List<StockMovement> findByProductIdAndCreatedAtBetweenOrderByCreatedAtDesc(
            UUID productId,
            OffsetDateTime start,
            OffsetDateTime end
    );
}
