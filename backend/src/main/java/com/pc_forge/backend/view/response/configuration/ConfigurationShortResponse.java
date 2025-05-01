package com.pc_forge.backend.view.response.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConfigurationShortResponse {
    private String name;
    private Double price;
    private List<ProductConfigurationResponse> products;
}
