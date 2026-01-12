package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.stock.StockMovementReportDTO;
import com.torresdev.inventory.entity.MovementType;
import com.torresdev.inventory.entity.StockMovement;
import com.torresdev.inventory.exception.InsufficientStockException;
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

    // =========================
    // ENTRADA DE ESTOQUE
    // =========================
    public void registerEntry(UUID productId, Integer quantity) {

        StockMovement movement = new StockMovement(
                productId,
                quantity,
                MovementType.ENTRY,
                OffsetDateTime.now()
        );

        repository.save(movement);
    }

    // =========================
    // SAÍDA DE ESTOQUE
    // =========================
    public void registerExit(UUID productId, Integer quantity) {

        int currentStock = calculateCurrentStock(productId);

        if (currentStock < quantity) {
            throw new InsufficientStockException("Insufficient stock for exit");
        }

        StockMovement movement = new StockMovement(
                productId,
                quantity,
                MovementType.EXIT,
                OffsetDateTime.now()
        );

        repository.save(movement);
    }

    // =========================
    // SALDO ATUAL
    // =========================
    public int calculateCurrentStock(UUID productId) {

        List<StockMovement> movements = repository.findByProductId(productId);

        int stock = 0;

        for (StockMovement movement : movements) {
            if (movement.getMovementType() == MovementType.ENTRY) {
                stock += movement.getQuantity();
            } else {
                stock -= movement.getQuantity();
            }
        }

        return stock;
    }

    public List<StockMovementReportDTO> generateReport() {

        return repository.findAll()
                .stream()
                .map(movement -> new StockMovementReportDTO(
                        movement.getProductId(),
                        movement.getMovementType(),
                        movement.getQuantity(),
                        movement.getCreatedAt()
                ))
                .toList();
    }
}
