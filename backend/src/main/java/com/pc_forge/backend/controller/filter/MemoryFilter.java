package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class MemoryFilter extends ProductFilter {
    private List<String> selectedTypes;
    private List<Integer> selectedCapacities;
    private List<Integer> selectedFrequencies;
    private List<Integer> selectedModules;
    private List<String> selectedLatencies;
    private Boolean lightning;

    public MemoryFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }


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

    @Override
    protected void setFilter() {
        splitJoinedParams();
        setPriceMinimum(getDoubleFromRequestParam(RequestParams.PRICE_MINIMUM));
        setPriceMaximum(getDoubleFromRequestParam(RequestParams.PRICE_MAXIMUM));
        setSelectedProducers(getStringListFromRequestParam(RequestParams.PRODUCER));
        selectedTypes = getStringListFromRequestParam(RequestParams.RAM_TYPE);
        selectedCapacities = getIntegerListFromRequestParam(RequestParams.CAPACITY);
        selectedFrequencies = getIntegerListFromRequestParam(RequestParams.FREQUENCY);
        selectedModules = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_MODULES);
        selectedLatencies = getStringListFromRequestParam(RequestParams.LATENCY);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
