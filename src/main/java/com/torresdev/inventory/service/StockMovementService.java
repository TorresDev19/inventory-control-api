package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.stock.StockMovementResponseDTO;
import com.torresdev.inventory.entity.MovementType;
import com.torresdev.inventory.entity.StockMovement;
import com.torresdev.inventory.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository) {
        this.stockMovementRepository = stockMovementRepository;
    }

    /*
     * =========================
     * LISTAGENS
     * =========================
     */

    public List<StockMovementResponseDTO> listByProduct(UUID productId) {
        return stockMovementRepository
                .findByProductIdOrderByCreatedAtDesc(productId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<StockMovementResponseDTO> listByProductAndType(
            UUID productId,
            MovementType movementType) {
        return stockMovementRepository
                .findByProductIdAndMovementTypeOrderByCreatedAtDesc(productId, movementType)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<StockMovementResponseDTO> listByPeriod(
            UUID productId,
            OffsetDateTime start,
            OffsetDateTime end) {
        return stockMovementRepository
                .findByProductIdAndCreatedAtBetweenOrderByCreatedAtDesc(
                        productId,
                        start,
                        end)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    /*
     * =========================
     * CRIAÇÃO DE MOVIMENTAÇÕES
     * =========================
     */

    public StockMovementResponseDTO createEntry(UUID productId, Integer quantity, String observacao) {
        StockMovement movement = new StockMovement(
                productId,
                quantity,
                MovementType.ENTRY,
                OffsetDateTime.now(),
                observacao);
        StockMovement saved = stockMovementRepository.save(movement);
        return toDTO(saved);
    }

    public StockMovementResponseDTO createExit(UUID productId, Integer quantity, String observacao) {
        StockMovement movement = new StockMovement(
                productId,
                quantity,
                MovementType.EXIT,
                OffsetDateTime.now(),
                observacao);
        StockMovement saved = stockMovementRepository.save(movement);
        return toDTO(saved);
    }

    /*
     * =========================
     * CÁLCULO DE ESTOQUE
     * =========================
     */

    public int calculateCurrentStock(UUID productId) {
        return stockMovementRepository
                .findByProductIdOrderByCreatedAtDesc(productId)
                .stream()
                .mapToInt(movement -> {
                    if (movement.getMovementType() == MovementType.ENTRY) {
                        return movement.getQuantity();
                    }
                    return -movement.getQuantity();
                })
                .sum();
    }

    /*
     * =========================
     * MAPPER
     * =========================
     */

    private StockMovementResponseDTO toDTO(StockMovement movement) {
        return new StockMovementResponseDTO(
                movement.getId(),
                movement.getProductId(),
                movement.getMovementType().name(),
                movement.getQuantity(),
                movement.getCreatedAt(),
                movement.getObservacao());
    }
}
