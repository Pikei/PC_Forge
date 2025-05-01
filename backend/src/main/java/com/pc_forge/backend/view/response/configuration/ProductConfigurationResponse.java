package com.pc_forge.backend.view.response.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductConfigurationResponse {
    private Long productEan;
    private String productName;
    private Double productPrice;
    private String productCategory;
    private String producer;
}
