package com.pc_forge.backend.view.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MotherboardResponse extends ProductResponse {
    private String chipset;
    private String memoryStandard;
    private String standard;
    private Integer memorySlots;
    private Boolean bluetooth;
    private Boolean wifi;
}
