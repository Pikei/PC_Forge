package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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

    public MotherboardFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = checkCommonFilterFieldsIfEmpty();
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

    @Override
    public void setFilter() {
        setCommonFilters();
        selectedSockets = getStringListFromRequestParam(RequestParams.SOCKET);
        selectedStandards = getStringListFromRequestParam(RequestParams.MOTHERBOARD_STANDARD);
        selectedChipsets = getStringListFromRequestParam(RequestParams.CHIPSET);
        selectedMemoryStandards = getStringListFromRequestParam(RequestParams.RAM_TYPE);
        selectedMemorySlots = getIntegerListFromRequestParam(RequestParams.RAM_SLOTS);
        selectedMaxMemoryCapacity = getIntegerListFromRequestParam(RequestParams.RAM_CAPACITY);
        selectedFrequencies = getIntegerListFromRequestParam(RequestParams.FREQUENCY);
        bluetooth = getBooleanFromRequestParam(RequestParams.BLUETOOTH);
        wifi = getBooleanFromRequestParam(RequestParams.WIFI);
        minWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MINIMUM);
        maxWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MAXIMUM);
        minDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MINIMUM);
        maxDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MAXIMUM);
    }
}
