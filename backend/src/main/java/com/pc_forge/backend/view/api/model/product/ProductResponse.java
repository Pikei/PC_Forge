package com.pc_forge.backend.view.api.model.product;

import lombok.Data;

@Data
public abstract class ProductResponse {
    private String name;
    private Double price;
}
