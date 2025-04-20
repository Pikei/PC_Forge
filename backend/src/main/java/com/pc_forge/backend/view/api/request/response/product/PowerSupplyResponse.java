package com.pc_forge.backend.view.api.request.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PowerSupplyResponse extends ProductResponse {
    private String standard;
    private Integer power;
    private String efficiencyCertificate;
    private Integer efficiency;
    private Boolean modularCabling;
}
