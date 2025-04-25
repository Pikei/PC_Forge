package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Data;

import java.util.List;

@Data
public abstract class ProductFilterResponse {
    @JsonProperty(RequestParams.PRICE_MINIMUM)
    private Double priceMinimum;

    @JsonProperty(RequestParams.PRICE_MAXIMUM)
    private Double priceMaximum;

    @JsonProperty(RequestParams.PRODUCER)
    private List<Object[]> producers;
}
