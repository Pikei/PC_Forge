package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class CoolerFilter extends ProductFilter {
    private List<String> selectedSockets;
    private List<Integer> selectedFans;
    private List<Integer> selectedFanDiameters;
    private List<Double> noiseLevel;
    private Boolean lightning;

    public CoolerFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
    }

    protected Boolean checkCommonCoolerFiltersIfEmpty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedSockets == null || selectedSockets.isEmpty());
        result = result && (selectedFans == null || selectedFans.isEmpty());
        result = result && (selectedFanDiameters == null || selectedFanDiameters.isEmpty());
        result = result && (noiseLevel == null || noiseLevel.isEmpty());
        result = result && (lightning == null);
        return result;
    }

    protected void setCommonCoolerFilters() {
        setCommonFilters();
        selectedSockets = getStringListFromRequestParam(RequestParams.SOCKET);
        selectedFans = getIntegerListFromRequestParam(RequestParams.NUMBER_OF_FANS);
        selectedFanDiameters = getIntegerListFromRequestParam(RequestParams.FAN_DIAMETER);
        noiseLevel = getDoubleListFromRequestParam(RequestParams.NOISE_LEVEL);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
