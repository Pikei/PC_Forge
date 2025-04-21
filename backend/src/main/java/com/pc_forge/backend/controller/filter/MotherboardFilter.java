package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.model.database.product.compatibility.MotherboardStandard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class MotherboardFilter extends ProductFilter {
    private List<String> selectedSockets;
    private List<String> selectedStandards;
    private List<String> selectedChipsets;
    private List<String> selectedMemoryStandards;
    private List<Integer> selectedMemorySlots;
    private List<Integer> selectedMaxMemoryCapacity;
    private List<Integer> selectedFrequencies;
    private Boolean bluetooth;
    private Boolean wifi;
    private Double minWidth;
    private Double maxWidth;
    private Double minDepth;
    private Double maxDepth;
}
