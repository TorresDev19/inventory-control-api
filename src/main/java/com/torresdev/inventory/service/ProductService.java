package com.torresdev.inventory.service;

import com.torresdev.inventory.dto.product.ProductRequestDTO;
import com.torresdev.inventory.dto.product.ProductResponseDTO;
import com.torresdev.inventory.entity.Product;
import com.torresdev.inventory.exception.ProductNotFoundException;
import com.torresdev.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public ProductResponseDTO create(ProductRequestDTO request) {

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getMinimumStock()
        );

        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    // LIST ALL (only active)
    public List<ProductResponseDTO> listAll() {
        return productRepository.findAll()
                .stream()
                .filter(Product::isActive)
                .map(this::toResponseDTO)
                .toList();
    }

    // FIND BY ID
    public ProductResponseDTO findById(UUID id) {
        Product product = productRepository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return toResponseDTO(product);
    }

    // UPDATE
    public ProductResponseDTO update(UUID id, ProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.update(
                request.getName(),
                request.getDescription(),
                request.getMinimumStock()
        );

        Product updated = productRepository.save(product);
        return toResponseDTO(updated);
    }

    // SOFT DELETE
    public void deactivate(UUID id) {
        Product product = productRepository.findById(id)
                .filter(Product::isActive)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        product.deactivate();
        productRepository.save(product);
    }

    // ===== Mapper =====
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
}
