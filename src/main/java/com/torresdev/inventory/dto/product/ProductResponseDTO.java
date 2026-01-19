package com.torresdev.inventory.dto.product;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductResponseDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private Integer estoqueMinimo;
    private boolean active;
    private OffsetDateTime createdAt;

    public ProductResponseDTO(
            UUID id,
            String nome,
            String descricao,
            Integer estoqueMinimo,
            boolean active,
            OffsetDateTime createdAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.estoqueMinimo = estoqueMinimo;
        this.active = active;
        this.createdAt = createdAt;
    }

    // ========= GETTERS =========

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
