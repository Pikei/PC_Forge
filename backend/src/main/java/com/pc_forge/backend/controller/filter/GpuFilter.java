package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class GpuFilter extends ProductFilter {
    private List<String> selectedChipsetProducers;
    private List<String> selectedChipsets;
    private List<Integer> selectedRamCapacities;
    private List<String> selectedRamTypes;
    private List<String> selectedDlls;
    private List<String> selectedConnectors;
    private List<String> selectedResolutions;
    private List<Integer> selectedRecommendedPs;
    private List<String> selectedCoolingTypes;
    private List<Integer> selectedFans;
    private Double minLength;
    private Double maxLength;
    private Boolean lightning;

    public GpuFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedChipsetProducers == null || selectedChipsetProducers.isEmpty());
        result = result && (selectedChipsets == null || selectedChipsets.isEmpty());
        result = result && (selectedRamCapacities == null || selectedRamCapacities.isEmpty());
        result = result && (selectedRamTypes == null || selectedRamTypes.isEmpty());
        result = result && (selectedDlls == null || selectedDlls.isEmpty());
        result = result && (selectedConnectors == null || selectedConnectors.isEmpty());
        result = result && (selectedResolutions == null || selectedResolutions.isEmpty());
        result = result && (selectedRecommendedPs == null || selectedRecommendedPs.isEmpty());
        result = result && (selectedCoolingTypes == null || selectedCoolingTypes.isEmpty());
        result = result && (selectedFans == null || selectedFans.isEmpty());
        result = result && minLength == null && maxLength == null;
        result = result && (lightning == null);
        return result;
    }

    @Override
    public void setFilter() {
        setCommonFilters();
        selectedChipsetProducers = getStringListFromRequestParam(RequestParams.CHIPSET_PRODUCER);
        selectedChipsets = getStringListFromRequestParam(RequestParams.CHIPSET);
        selectedRamCapacities = getIntegerListFromRequestParam(RequestParams.RAM_CAPACITY);
        selectedRamTypes = getStringListFromRequestParam(RequestParams.RAM_TYPE);
        selectedDlls = getStringListFromRequestParam(RequestParams.DLSS);
        selectedConnectors = getStringListFromRequestParam(RequestParams.CONNECTOR);
        selectedResolutions = getStringListFromRequestParam(RequestParams.RESOLUTION);
        selectedRecommendedPs = getIntegerListFromRequestParam(RequestParams.PS_POWER);
        selectedCoolingTypes = getStringListFromRequestParam(RequestParams.COOLING_TYPE);
        selectedFans = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_FANS);
        minLength = getDoubleFromRequestParam(RequestParams.LENGTH_MINIMUM);
        maxLength = getDoubleFromRequestParam(RequestParams.LENGTH_MAXIMUM);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
