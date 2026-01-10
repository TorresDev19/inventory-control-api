package com.torresdev.inventory.controller;

import com.torresdev.inventory.entity.StockMovement;
import com.torresdev.inventory.service.StockMovementService;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    // =========================
    // ENTRADA DE ESTOQUE
    // =========================
    @PostMapping("/entry")
    public void registerEntry(
            @RequestParam UUID productId,
            @RequestParam @Min(1) Integer quantity
    ) {
        stockMovementService.registerEntry(productId, quantity);
    }

    // =========================
    // SAÍDA DE ESTOQUE
    // =========================
    @PostMapping("/exit")
    public void registerExit(
            @RequestParam UUID productId,
            @RequestParam @Min(1) Integer quantity
    ) {
        stockMovementService.registerExit(productId, quantity);
    }

    // =========================
    // SALDO ATUAL
    // =========================
    @GetMapping("/{productId}/balance")
    public int getCurrentStock(@PathVariable UUID productId) {
        return stockMovementService.calculateCurrentStock(productId);
    }

    // =========================
    // HISTÓRICO DE MOVIMENTAÇÕES
    // =========================
    @GetMapping("/{productId}")
    public List<StockMovement> listMovements(@PathVariable UUID productId) {
        return stockMovementService.listByProduct(productId);
    }
}
