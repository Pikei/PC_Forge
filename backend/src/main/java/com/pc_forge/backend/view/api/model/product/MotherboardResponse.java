package com.pc_forge.backend.view.api.model.product;

import com.pc_forge.backend.model.database.product.compatibility.MotherboardStandard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MotherboardResponse extends ProductResponse {
    private String chipset;
    private String memoryStandard;
    private Integer memorySlots;
    private Boolean bluetooth;
    private Boolean wifi;
    private MotherboardStandard standard;
}
