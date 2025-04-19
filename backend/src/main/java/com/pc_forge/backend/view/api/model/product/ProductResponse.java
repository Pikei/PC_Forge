package com.pc_forge.backend.view.api.model.product;

import lombok.Data;

@Data
public abstract class ProductResponse {
    private Long id;
    private String name;
    private Double price;
}
