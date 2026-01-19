package com.torresdev.inventory.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {

    @NotBlank(message = "Nome do produto é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "Estoque mínimo é obrigatório")
    @Min(value = 0, message = "Estoque mínimo deve ser zero ou maior")
    private Integer estoqueMinimo;

    public ProductRequestDTO() {
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
}
