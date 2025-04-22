package com.pc_forge.backend.controller.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class GpuFilter extends ProductFilter {
    private List<String> selectedChipsetProducers;
    private List<String> selectedChipsets;
    private List<Integer> selectedRamCapacities;
    private List<String> selectedRamTypes;
    private List<String> selectedDlls;
    private List<String> selectedConnectors;
    private List<Integer> selectedLengths;
    private List<String> selectedResolutions;
    private List<Integer> selectedRecommendedPs;
    private List<String> selectedCoolingTypes;
    private List<Integer> selectedFans;
    private Boolean lightning;

    @Override
    public Boolean empty() {
        boolean result = getPriceMaximum() == null && getPriceMinimum() == null;
        result = result && (getSelectedProducers() == null || getSelectedProducers().isEmpty());
        result = result && (selectedChipsetProducers == null || selectedChipsetProducers.isEmpty());
        result = result && (selectedChipsets == null || selectedChipsets.isEmpty());
        result = result && (selectedRamCapacities == null || selectedRamCapacities.isEmpty());
        result = result && (selectedRamTypes == null || selectedRamTypes.isEmpty());
        result = result && (selectedDlls == null || selectedDlls.isEmpty());
        result = result && (selectedConnectors == null || selectedConnectors.isEmpty());
        result = result && (selectedLengths == null || selectedLengths.isEmpty());
        result = result && (selectedResolutions == null || selectedResolutions.isEmpty());
        result = result && (selectedRecommendedPs == null || selectedRecommendedPs.isEmpty());
        result = result && (selectedCoolingTypes == null || selectedCoolingTypes.isEmpty());
        result = result && (selectedFans == null || selectedFans.isEmpty());
        result = result && (lightning != null);
        return result;
    }
}
