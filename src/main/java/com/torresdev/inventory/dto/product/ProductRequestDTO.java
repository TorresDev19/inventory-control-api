package com.torresdev.inventory.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Minimum stock is required")
    @Min(value = 0, message = "Minimum stock must be zero or greater")
    private Integer minimumStock;

    public ProductRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMinimumStock() {
        return minimumStock;
    }
}