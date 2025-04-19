package com.pc_forge.backend.view.api;

import lombok.Getter;

@Getter
public enum ProductCategoryUrl {
    computer_cases(ProductCategoryCode.CASE.getCode()),
    air_cooling_systems(ProductCategoryCode.AIR_COOLER.getCode()),
    liquid_cooling_systems(ProductCategoryCode.LIQUID_COOLER.getCode()),
    graphics_cards(ProductCategoryCode.GRAPHICS_CARD.getCode()),
    solid_state_drives(ProductCategoryCode.SSD.getCode()),
    hard_disk_drives(ProductCategoryCode.HDD.getCode()),
    motherboards(ProductCategoryCode.MOTHERBOARD.getCode()),
    power_supplies(ProductCategoryCode.POWER_SUPPLY.getCode()),
    processors(ProductCategoryCode.PROCESSOR.getCode()),
    ram_memories(ProductCategoryCode.RAM.getCode());

    private final String code;

    ProductCategoryUrl(String code) {
        this.code = code;
    }
}
