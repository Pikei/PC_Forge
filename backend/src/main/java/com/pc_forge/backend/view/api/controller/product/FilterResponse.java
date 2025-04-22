package com.pc_forge.backend.view.api.controller.product;

import com.pc_forge.backend.view.api.request.response.filter.ProductFilterResponse;
import org.springframework.http.ResponseEntity;

public interface FilterResponse {
    ResponseEntity<? extends ProductFilterResponse> getProductFilters();
}
