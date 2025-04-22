package com.pc_forge.backend.controller.filter;

import lombok.Data;

import java.util.List;

@Data
public abstract class ProductFilter {
    private Double priceMinimum;
    private Double priceMaximum;
    private List<String> selectedProducers;

    public abstract Boolean empty();
}
