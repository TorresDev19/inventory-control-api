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
import java.util.stream.Collectors;

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

    // =========================
    // CREATE
    // =========================
    public ProductResponseDTO create(ProductRequestDTO request) {

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getMinimumStock()
        );

        return toResponseDTO(productRepository.save(product));
    }

    // =========================
    // LIST ALL
    // =========================
    public List<ProductResponseDTO> listAll() {
        return productRepository.findAllByActiveTrue()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // LIST ALL WITH STOCK
    // =========================
    public List<ProductWithStockResponseDTO> listAllWithStock() {
        return productRepository.findAllByActiveTrue()
                .stream()
                .map(this::toWithStockDTO)
                .collect(Collectors.toList());
    }

    // =========================
    // FIND BY ID
    // =========================
    public ProductResponseDTO findById(UUID id) {
        return toResponseDTO(findActiveProduct(id));
    }

    public ProductWithStockResponseDTO findByIdWithStock(UUID id) {
        return toWithStockDTO(findActiveProduct(id));
    }

    // =========================
    // UPDATE
    // =========================
    public ProductResponseDTO update(UUID id, ProductRequestDTO request) {

        Product product = findActiveProduct(id);

        product.update(
                request.getName(),
                request.getDescription(),
                request.getMinimumStock()
        );

        return toResponseDTO(productRepository.save(product));
    }

    // =========================
    // DEACTIVATE
    // =========================
    public void deactivate(UUID id) {
        Product product = findActiveProduct(id);
        product.deactivate();
        productRepository.save(product);
    }

    // =========================
    // INTERNAL HELPERS
    // =========================
    private Product findActiveProduct(UUID id) {
        return productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getMinimumStock(),
                product.isActive(),
                product.getCreatedAt()
        );
    }

    private ProductWithStockResponseDTO toWithStockDTO(Product product) {

        int currentStock =
                stockMovementService.calculateCurrentStock(product.getId());

        return new ProductWithStockResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getMinimumStock(),
                currentStock,
                product.isActive(),
                product.getCreatedAt()
        );
    }


    public List<ProductWithStockResponseDTO> listLowStock() {

        return productRepository.findAllByActiveTrue()
                .stream()
                .map(this::toWithStockDTO)
                .filter(p -> p.getCurrentStock() < p.getMinimumStock())
                .toList();
    }

}
