package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class CoolerResponse extends ProductResponse {
    private Boolean lightning;
    private Integer fanDiameter;
    private Integer numberOfFans;
    private List<String> socket;
}
