package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.product.ProductRequestDTO;
import com.torresdev.inventory.dto.product.ProductResponseDTO;
import com.torresdev.inventory.dto.product.ProductWithStockResponseDTO;
import com.torresdev.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO request) {
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponseDTO> listAll() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid ProductRequestDTO request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deactivate(@PathVariable UUID id) {
        productService.deactivate(id);
    }

    @GetMapping("/estoque-baixo")
    public List<ProductWithStockResponseDTO> lowStock() {
        return productService.listLowStock();
    }
}
