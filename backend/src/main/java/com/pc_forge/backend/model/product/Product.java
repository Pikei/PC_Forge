package com.pc_forge.backend.model.product;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public abstract class Product {
    @Id
    private Long ean;
    private String name;
    private String producer;
    private String category;
    private String description;
    private Double price;
    private String producerCode;
}
