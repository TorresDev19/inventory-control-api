package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.stock.StockMovementRequestDTO;
import com.torresdev.inventory.dto.stock.StockMovementResponseDTO;
import com.torresdev.inventory.entity.MovementType;
import com.torresdev.inventory.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/movimentacoes")
public class StockMovementController {

        private final StockMovementService stockMovementService;

        public StockMovementController(StockMovementService stockMovementService) {
                this.stockMovementService = stockMovementService;
        }

        @PostMapping("/entrada")
        public ResponseEntity<StockMovementResponseDTO> createEntry(
                        @RequestBody @Valid StockMovementRequestDTO request) {
                StockMovementResponseDTO response = stockMovementService.createEntry(
                                request.getProductId(),
                                request.getQuantity(),
                                request.getNote());
                return ResponseEntity.ok(response);
        }

        @PostMapping("/saida")
        public ResponseEntity<StockMovementResponseDTO> createExit(
                        @RequestBody @Valid StockMovementRequestDTO request) {
                StockMovementResponseDTO response = stockMovementService.createExit(
                                request.getProductId(),
                                request.getQuantity(),
                                request.getNote());
                return ResponseEntity.ok(response);
        }

        @GetMapping("/product/{productId}")
        public List<StockMovementResponseDTO> listByProduct(
                        @PathVariable UUID productId) {
                return stockMovementService.listByProduct(productId);
        }

        @GetMapping("/product/{productId}/type/{type}")
        public List<StockMovementResponseDTO> listByProductAndType(
                        @PathVariable UUID productId,
                        @PathVariable String type) {
                return stockMovementService.listByProductAndType(
                                productId,
                                MovementType.valueOf(type.toUpperCase()));
        }

        @GetMapping("/product/{productId}/period")
        public List<StockMovementResponseDTO> listByPeriod(
                        @PathVariable UUID productId,
                        @RequestParam OffsetDateTime start,
                        @RequestParam OffsetDateTime end) {
                return stockMovementService.listByPeriod(productId, start, end);
        }
}
