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
                        StockMovementService stockMovementService) {
                this.productRepository = productRepository;
                this.stockMovementService = stockMovementService;
        }

        // =========================
        // CREATE
        // =========================
        public ProductResponseDTO create(ProductRequestDTO request) {

                Product product = new Product(
                                request.getNome(),
                                request.getDescricao(),
                                request.getEstoqueMinimo());

                return toResponseDTO(productRepository.save(product));
        }

        // =========================
        // LIST ALL
        // =========================
        public List<ProductResponseDTO> listAll() {
                return productRepository.findAll()
                                .stream()
                                .filter(Product::isActive)
                                .map(this::toResponseDTO)
                                .toList();
        }

        // =========================
        // FIND BY ID
        // =========================
        public ProductResponseDTO findById(UUID id) {
                Product product = productRepository.findById(id)
                                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

                return toResponseDTO(product);
        }

        // =========================
        // UPDATE
        // =========================
        public ProductResponseDTO update(UUID id, ProductRequestDTO request) {

                Product product = productRepository.findById(id)
                                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

                product.update(
                                request.getNome(),
                                request.getDescricao(),
                                request.getEstoqueMinimo());

                return toResponseDTO(productRepository.save(product));
        }

        // =========================
        // DEACTIVATE
        // =========================
        public void deactivate(UUID id) {

                Product product = productRepository.findById(id)
                                .orElseThrow(() -> new ProductNotFoundException("Produto não encontrado"));

                product.deactivate();
                productRepository.save(product);
        }

        // =========================
        // LOW STOCK
        // =========================
        public List<ProductWithStockResponseDTO> listLowStock() {

                return productRepository.findAll()
                                .stream()
                                .filter(Product::isActive)
                                .map(product -> {

                                        int currentStock = stockMovementService.calculateCurrentStock(product.getId());

                                        return new ProductWithStockResponseDTO(
                                                        product.getId(),
                                                        product.getName(),
                                                        product.getDescription(),
                                                        product.getMinimumStock(),
                                                        currentStock,
                                                        product.isActive(),
                                                        product.getCreatedAt());
                                })
                                .filter(dto -> dto.getQuantidadeAtual() < dto.getEstoqueMinimo())
                                .toList();
        }

        // =========================
        // MAPPER
        // =========================
        private ProductResponseDTO toResponseDTO(Product product) {
                return new ProductResponseDTO(
                                product.getId(),
                                product.getName(),
                                product.getDescription(),
                                product.getMinimumStock(),
                                product.isActive(),
                                product.getCreatedAt());
        }
}
