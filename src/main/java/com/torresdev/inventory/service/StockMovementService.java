package com.torresdev.inventory.service;

import com.torresdev.inventory.entity.StockMovement;
import com.torresdev.inventory.entity.MovementType;
import com.torresdev.inventory.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockMovementService {

    private final StockMovementRepository repository;

    public StockMovementService(StockMovementRepository repository) {
        this.repository = repository;
    }

    public void registerEntry(UUID productId, Integer quantity) {
        StockMovement movement = new StockMovement(
                productId,
                quantity,
                MovementType.ENTRY,
                OffsetDateTime.now()
        );

        repository.save(movement);
    }

    public void registerExit(UUID productId, Integer quantity) {
        StockMovement movement = new StockMovement(
                productId,
                quantity,
                MovementType.EXIT,
                OffsetDateTime.now()
        );

        repository.save(movement);
    }

    public List<StockMovement> listByProduct(UUID productId) {
        return repository.findByProductId(productId);
    }
}