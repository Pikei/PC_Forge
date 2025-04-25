package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class CaseFilter extends ProductFilter {
    private List<String> selectedColors;
    private List<String> selectedCaseTypes;
    private Double minHeight;
    private Double maxHeight;
    private Double minWidth;
    private Double maxWidth;
    private Double minDepth;
    private Double maxDepth;
    private Boolean hasWindow;
    private Boolean hasPowerSupply;
    private List<Integer> selectedPsPowers;
    private Integer maxGpuLength;
    private Integer maxCpuCoolerHeight;
    private List<String> selectedFrontFans;
    private List<String> selectedBackFans;
    private List<String> selectedSideFans;
    private List<String> selectedBottomFans;
    private List<String> selectedTopFans;
    private List<Integer> selectedUsb20;
    private List<Integer> selectedUsb30;
    private List<Integer> selectedUsb31;
    private List<Integer> selectedUsb32;
    private List<Integer> selectedUsbC;

    public CaseFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = getPriceMaximum() == null && getPriceMinimum() == null;
        result = result && (getSelectedProducers() == null || getSelectedProducers().isEmpty());
        result = result && (selectedColors == null || selectedColors.isEmpty());
        result = result && (selectedCaseTypes == null || selectedCaseTypes.isEmpty());
        result = result && minHeight == null && maxHeight == null;
        result = result && minWidth == null && maxWidth == null;
        result = result && minDepth == null && maxDepth == null;
        result = result && (hasWindow == null);
        result = result && (hasPowerSupply == null);
        result = result && (selectedPsPowers == null || selectedPsPowers.isEmpty());
        result = result && (maxGpuLength == null);
        result = result && (maxCpuCoolerHeight == null);
        result = result && (selectedFrontFans == null || selectedFrontFans.isEmpty());
        result = result && (selectedBackFans == null || selectedBackFans.isEmpty());
        result = result && (selectedSideFans == null || selectedSideFans.isEmpty());
        result = result && (selectedBottomFans == null || selectedBottomFans.isEmpty());
        result = result && (selectedTopFans == null || selectedTopFans.isEmpty());
        result = result && (selectedUsb20 == null || selectedUsb20.isEmpty());
        result = result && (selectedUsb30 == null || selectedUsb30.isEmpty());
        result = result && (selectedUsb31 == null || selectedUsb31.isEmpty());
        result = result && (selectedUsb32 == null || selectedUsb32.isEmpty());
        return result;
    }

    @Override
    protected void setFilter() {
        splitJoinedParams();
        setPriceMinimum(getDoubleFromRequestParam(RequestParams.PRICE_MINIMUM));
        setPriceMaximum(getDoubleFromRequestParam(RequestParams.PRICE_MAXIMUM));
        setSelectedProducers(getStringListFromRequestParam(RequestParams.PRODUCER));
        selectedColors = getStringListFromRequestParam(RequestParams.COLOR);
        selectedCaseTypes = getStringListFromRequestParam(RequestParams.CASE_TYPE);
        minHeight = getDoubleFromRequestParam(RequestParams.HEIGHT_MINIMUM);
        maxHeight = getDoubleFromRequestParam(RequestParams.HEIGHT_MAXIMUM);
        minWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MINIMUM);
        maxWidth = getDoubleFromRequestParam(RequestParams.WIDTH_MAXIMUM);
        minDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MINIMUM);
        maxDepth = getDoubleFromRequestParam(RequestParams.DEPTH_MAXIMUM);
        hasWindow = getBooleanFromRequestParam(RequestParams.HAS_WINDOW);
        hasPowerSupply = getBooleanFromRequestParam(RequestParams.HAS_POWER_SUPPLY);
        selectedPsPowers = getIntegerListFromRequestParam(RequestParams.PS_POWER);
        maxGpuLength = getIntegerFromRequestParam(RequestParams.MAX_GPU_LENGTH);
        maxCpuCoolerHeight = getIntegerFromRequestParam(RequestParams.MAX_CPU_HEIGHT);
        selectedFrontFans = getStringListFromRequestParam(RequestParams.FRONT_FANS);
        selectedBackFans = getStringListFromRequestParam(RequestParams.BACK_FANS);
        selectedSideFans = getStringListFromRequestParam(RequestParams.SIDE_FANS);
        selectedBottomFans = getStringListFromRequestParam(RequestParams.BOTTOM_FANS);
        selectedTopFans = getStringListFromRequestParam(RequestParams.TOP_FANS);
        selectedUsb20 = getIntegerListFromRequestParam(RequestParams.USB_20);
        selectedUsb30 = getIntegerListFromRequestParam(RequestParams.USB_30);
        selectedUsb31 = getIntegerListFromRequestParam(RequestParams.USB_31);
        selectedUsb32 = getIntegerListFromRequestParam(RequestParams.USB_32);
        selectedUsbC = getIntegerListFromRequestParam(RequestParams.USB_C);
    }
}
