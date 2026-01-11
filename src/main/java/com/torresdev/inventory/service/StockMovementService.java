package com.torresdev.inventory.service;

import com.torresdev.inventory.entity.MovementType;
import com.torresdev.inventory.entity.Product;
import com.torresdev.inventory.entity.StockMovement;
import com.torresdev.inventory.exception.InsufficientStockException;
import com.torresdev.inventory.exception.ProductNotFoundException;
import com.torresdev.inventory.repository.ProductRepository;
import com.torresdev.inventory.repository.StockMovementRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(
            StockMovementRepository stockMovementRepository,
            ProductRepository productRepository
    ) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    // =========================
    // ENTRADA DE ESTOQUE
    // =========================
    public void registerEntry(UUID productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        StockMovement movement = new StockMovement(
                product.getId(),
                quantity,
                MovementType.ENTRY,
                OffsetDateTime.now()
        );

        stockMovementRepository.save(movement);
    }

    // =========================
    // SAÍDA DE ESTOQUE (COM REGRA)
    // =========================
    public void registerExit(UUID productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        int currentStock = calculateCurrentStock(productId);
        int resultingStock = currentStock - quantity;

        if (resultingStock < product.getMinimumStock()) {
            throw new InsufficientStockException(
                    "Insufficient stock. Minimum allowed: " + product.getMinimumStock()
            );
        }

        StockMovement movement = new StockMovement(
                product.getId(),
                quantity,
                MovementType.EXIT,
                OffsetDateTime.now()
        );

        stockMovementRepository.save(movement);
    }

    // =========================
    // CONSULTAS
    // =========================
    public List<StockMovement> listByProduct(UUID productId) {
        return stockMovementRepository.findByProductId(productId);
    }

    public int calculateCurrentStock(UUID productId) {

        List<StockMovement> movements =
                stockMovementRepository.findByProductId(productId);

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
}
