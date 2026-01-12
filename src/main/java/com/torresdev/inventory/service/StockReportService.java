package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.stock.StockReportResponseDTO;
import com.torresdev.inventory.entity.Product;
import com.torresdev.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockReportService {

    private final ProductRepository productRepository;
    private final StockMovementService stockMovementService;

    public StockReportService(
            ProductRepository productRepository,
            StockMovementService stockMovementService
    ) {
        this.productRepository = productRepository;
        this.stockMovementService = stockMovementService;
    }

    public List<StockReportResponseDTO> generateStockReport() {
        return productRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private StockReportResponseDTO toDTO(Product product) {
        int currentStock = stockMovementService.calculateCurrentStock(product.getId());

        String status = currentStock <= product.getMinimumStock()
                ? "LOW"
                : "OK";

        return new StockReportResponseDTO(
                product.getId(),
                product.getName(),
                product.getMinimumStock(),
                currentStock,
                status,
                product.getCreatedAt()
        );
    }
}
