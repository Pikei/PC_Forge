package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class GraphicsCardResponse extends ProductResponse {
    private String chipsetProducer;
    private String chipset;
    private Integer cardLength;
    private Integer ram;
}
