package com.pc_forge.backend.view.api;

import lombok.Getter;

@Getter
public enum ProductTypeCodes {
    CASE("CASE"),
    AIR_COOLER("AIR_COOLER"),
    LIQUID_COOLER("LIQUID_COOLER"),
    GPU("GPU"),
    SSD("SSD"),
    HDD("HDD"),
    MB("MB"),
    POWER_SUPPLY("POWER_SUPPLY"),
    CPU("CPU"),
    RAM("RAM");

    private final String code;

    ProductTypeCodes(final String code) {
        this.code = code;
    }

}
