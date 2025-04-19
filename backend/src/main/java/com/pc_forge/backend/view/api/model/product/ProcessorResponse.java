package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ProcessorResponse extends ProductResponse {
    private String socket;
    private Integer cores;
    private Integer threads;
    private Boolean unlocked;
    private Double frequency;
    private String integratedGraphicsUnit;
    private Boolean coolerIncluded;
}
