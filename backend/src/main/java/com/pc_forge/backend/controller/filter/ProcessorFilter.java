package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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

    public ProcessorFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

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

    @Override
    protected void setFilter() {
        splitJoinedParams();
        setPriceMinimum(getDoubleFromRequestParam(RequestParams.PRICE_MINIMUM));
        setPriceMaximum(getDoubleFromRequestParam(RequestParams.PRICE_MAXIMUM));
        setSelectedProducers(getStringListFromRequestParam(RequestParams.PRODUCER));
        selectedSockets = getStringListFromRequestParam(RequestParams.SOCKET);
        selectedLines = getStringListFromRequestParam(RequestParams.MODEL);
        selectedCores = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_CORES);
        selectedFrequencies = getDoubleListFromRequestParam(RequestParams.FREQUENCY);
        selectedGraphicsUnits = getStringListFromRequestParam(RequestParams.INTEGRATED_GRAPHICS_UNIT);
        selectedPackagingTypes = getStringListFromRequestParam(RequestParams.PACKAGING_TYPE);
        unlocked = getBooleanFromRequestParam(RequestParams.CORE_UNLOCKED);
        coolerIncluded = getBooleanFromRequestParam(RequestParams.COOLER_INCLUDED);
    }
}
