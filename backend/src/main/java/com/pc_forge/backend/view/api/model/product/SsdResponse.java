package com.pc_forge.backend.view.api.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class SsdResponse extends DriveResponse {
    private Integer readSpeed;
    private Integer writeSpeed;
}
