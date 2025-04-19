package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class SsdResponse extends ProductResponse {
    private Integer readSpeed;
    private Integer writeSpeed;
}
