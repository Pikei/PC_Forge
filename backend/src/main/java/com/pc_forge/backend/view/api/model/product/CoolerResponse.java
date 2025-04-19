package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CoolerResponse extends ProductResponse {
    private Boolean lightning;
    private Integer fanDiameter;
}
