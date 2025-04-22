package com.pc_forge.backend.controller.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class MemoryFilter extends ProductFilter {
    private List<String> selectedTypes;
    private List<Integer> selectedCapacities;
    private List<Integer> selectedFrequencies;
    private List<Integer> selectedModules;
    private List<String> selectedLatencies;
    private Boolean lightning;


    @Override
    public Boolean empty() {
        boolean result = getPriceMaximum() == null && getPriceMinimum() == null;
        result = result && (getSelectedProducers() == null || getSelectedProducers().isEmpty());
        result = result && (selectedTypes == null || selectedTypes.isEmpty());
        result = result && (selectedCapacities == null || selectedCapacities.isEmpty());
        result = result && (selectedFrequencies == null || selectedFrequencies.isEmpty());
        result = result && (selectedModules == null || selectedModules.isEmpty());
        result = result && (selectedLatencies == null || selectedLatencies.isEmpty());
        result = result && (lightning == null);
        return result;
    }
}
