package com.torresdev.inventory.controller;

import com.torresdev.inventory.dto.product.ProductRequestDTO;
import com.torresdev.inventory.dto.product.ProductResponseDTO;
import com.torresdev.inventory.dto.product.ProductWithStockResponseDTO;
import com.torresdev.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
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
    public ProductResponseDTO update(
            @PathVariable UUID id,
            @RequestBody @Valid ProductRequestDTO request
    ) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deactivate(@PathVariable UUID id) {
        productService.deactivate(id);
    }

    @GetMapping("/low-stock")
    public List<ProductWithStockResponseDTO> lowStock() {
        return productService.listLowStock();
    }
}
