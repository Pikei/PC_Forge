package com.pc_forge.backend.view.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderResponse {
    private Long productEan;
    private String productName;
    private Double productPrice;
    private Integer productQuantity;
    private String productCategory;
    private String producer;
}
