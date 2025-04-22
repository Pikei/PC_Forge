package com.pc_forge.backend.controller.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class ProcessorFilter extends ProductFilter {
    private List<String> selectedSockets;
    private List<String> selectedLines;
    private List<Integer> selectedCores;
    private List<Double> selectedFrequencies;
    private List<String> selectedGraphicsUnits;
    private List<String> selectedPackagingTypes;
    private Boolean unlocked;
    private Boolean coolerIncluded;

    @Override
    public Boolean empty() {
        boolean result = getPriceMaximum() == null && getPriceMinimum() == null;
        result = result && (getSelectedProducers() == null || getSelectedProducers().isEmpty());
        result = result && (selectedSockets == null || selectedSockets.isEmpty());
        result = result && (selectedLines == null || selectedLines.isEmpty());
        result = result && (selectedCores == null || selectedCores.isEmpty());
        result = result && (selectedFrequencies == null || selectedFrequencies.isEmpty());
        result = result && (selectedGraphicsUnits == null || selectedGraphicsUnits.isEmpty());
        result = result && (selectedPackagingTypes == null || selectedPackagingTypes.isEmpty());
        result = result && (unlocked == null);
        result = result && (coolerIncluded == null);
        return result;
    }
}
