package com.torresdev.inventory.dto.product;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductWithStockResponseDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private Integer estoqueMinimo;
    private int quantidadeAtual;
    private boolean active;
    private OffsetDateTime createdAt;

    public ProductWithStockResponseDTO(
            UUID id,
            String nome,
            String descricao,
            Integer estoqueMinimo,
            int quantidadeAtual,
            boolean active,
            OffsetDateTime createdAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.estoqueMinimo = estoqueMinimo;
        this.quantidadeAtual = quantidadeAtual;
        this.active = active;
        this.createdAt = createdAt;
    }

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

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public boolean isActive() {
        return active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
