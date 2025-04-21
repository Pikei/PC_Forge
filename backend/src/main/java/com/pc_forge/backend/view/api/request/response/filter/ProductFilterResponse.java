package com.pc_forge.backend.view.api.request.response.filter;

import lombok.Data;

import java.util.List;

@Data
public abstract class ProductFilterResponse {
    private Double priceMinimum;
    private Double priceMaximum;
    private List<Object[]> producers;
}
