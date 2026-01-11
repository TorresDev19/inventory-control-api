package com.torresdev.inventory.repository;

import com.torresdev.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    // =========================
    // PRODUTOS ATIVOS
    // =========================
    List<Product> findAllByActiveTrue();

    // =========================
    // PRODUTO ATIVO POR ID
    // =========================
    Optional<Product> findByIdAndActiveTrue(UUID id);
}
