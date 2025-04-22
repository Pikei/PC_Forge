package com.pc_forge.backend.controller;

import lombok.Getter;

@Getter
public enum ProductCategoryCode {
    CASE("CASE"),
    AIR_COOLER("AIR_COOLER"),
    LIQUID_COOLER("LIQUID_COOLER"),
    GRAPHICS_CARD("GPU"),
    SSD("SSD"),
    HDD("HDD"),
    MOTHERBOARD("MB"),
    POWER_SUPPLY("PS"),
    PROCESSOR("CPU"),
    RAM("RAM");

    private final String code;

    ProductCategoryCode(final String code) {
        this.code = code;
    }

}
