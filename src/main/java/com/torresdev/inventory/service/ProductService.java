package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.product.ProductRequestDTO;
import com.torresdev.inventory.dto.product.ProductResponseDTO;
import com.torresdev.inventory.dto.product.ProductWithStockResponseDTO;
import com.torresdev.inventory.entity.Product;
import com.torresdev.inventory.exception.ProductNotFoundException;
import com.torresdev.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StockMovementService stockMovementService;

    public ProductService(
            ProductRepository productRepository,
            StockMovementService stockMovementService
    ) {
        this.productRepository = productRepository;
        this.stockMovementService = stockMovementService;
    }

    // CREATE
    public ProductResponseDTO create(ProductRequestDTO request) {

        Product product = new Product(
                request.getName(),
                request.getMinimumStock()
        );

        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getMinimumStock(),
                saved.isActive(),
                saved.getCreatedAt()
        );
    }

    // LIST
    public List<ProductResponseDTO> listAll() {
        return productRepository.findAll()
                .stream()
                .filter(Product::isActive)
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getMinimumStock(),
                        p.isActive(),
                        p.getCreatedAt()
                ))
                .toList();
    }

    // FIND WITH STOCK
    public ProductWithStockResponseDTO findById(UUID id) {

        Product product = productRepository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        int stock = stockMovementService.calculateCurrentStock(id);

        return new ProductWithStockResponseDTO(
                product.getId(),
                product.getName(),
                stock,
                product.getMinimumStock(),
                product.isActive()
        );
    }

    // UPDATE
    public ProductResponseDTO update(UUID id, ProductRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.update(request.getName(), request.getMinimumStock());

        Product updated = productRepository.save(product);

        return new ProductResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getMinimumStock(),
                updated.isActive(),
                updated.getCreatedAt()
        );
    }

    // DEACTIVATE
    public void deactivate(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.deactivate();
        productRepository.save(product);
    }
}
