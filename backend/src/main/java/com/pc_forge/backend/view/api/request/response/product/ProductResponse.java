package com.pc_forge.backend.view.api.request.response.product;

import lombok.Data;

@Data
public abstract class ProductResponse {
    private Long id;
    private String category;
    private String name;
    private Double price;
    private String producer;
}
