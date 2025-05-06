package com.pc_forge.backend.view.response.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompatibilityResponse {
    private Boolean compatible;
    private String firstProductCategoryCode;
    private String secondProductCategoryCode;
    private String message;
}
