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

    @Override
    public Boolean empty() {
        boolean result = getPriceMaximum() == null && getPriceMinimum() == null;
        result = result && (getSelectedProducers() == null || getSelectedProducers().isEmpty());
        result = result && (selectedSockets == null || selectedSockets.isEmpty());
        result = result && (selectedStandards == null || selectedStandards.isEmpty());
        result = result && (selectedChipsets == null || selectedChipsets.isEmpty());
        result = result && (selectedMemoryStandards == null || selectedMemoryStandards.isEmpty());
        result = result && (selectedMemorySlots == null || selectedMemorySlots.isEmpty());
        result = result && (selectedMaxMemoryCapacity == null || selectedMaxMemoryCapacity.isEmpty());
        result = result && (selectedFrequencies == null || selectedFrequencies.isEmpty());
        result = result && bluetooth == null;
        result = result && wifi == null;
        result = result && minWidth == null && maxWidth == null;
        result = result && minDepth == null && maxDepth == null;
        return result;
    }
}
