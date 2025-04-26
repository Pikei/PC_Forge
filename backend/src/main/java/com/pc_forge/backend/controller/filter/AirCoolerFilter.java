package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class
AirCoolerFilter extends CoolerFilter {
    private List<String> selectedBaseMaterials;
    private Integer minHeight;
    private Integer maxHeight;
    private Integer minWidth;
    private Integer maxWidth;
    private Integer minDepth;
    private Integer maxDepth;
    private Boolean verticalInstallation;


    public AirCoolerFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = checkCommonCoolerFiltersIfEmpty();
        result = result && (selectedBaseMaterials == null || selectedBaseMaterials.isEmpty());
        result = result && minHeight == null && maxHeight == null;
        result = result && minWidth == null && maxWidth == null;
        result = result && minDepth == null && maxDepth == null;
        result = result && (verticalInstallation == null);
        return result;
    }

    @Override
    public void setFilter() {
        setCommonCoolerFilters();
        selectedBaseMaterials = getStringListFromRequestParam(RequestParams.BASE_MATERIAL);
        minHeight = getIntegerFromRequestParam(RequestParams.HEIGHT_MINIMUM);
        maxHeight = getIntegerFromRequestParam(RequestParams.HEIGHT_MAXIMUM);
        minWidth = getIntegerFromRequestParam(RequestParams.WIDTH_MINIMUM);
        maxWidth = getIntegerFromRequestParam(RequestParams.WIDTH_MAXIMUM);
        minDepth = getIntegerFromRequestParam(RequestParams.DEPTH_MINIMUM);
        maxDepth = getIntegerFromRequestParam(RequestParams.DEPTH_MAXIMUM);
        verticalInstallation = getBooleanFromRequestParam(RequestParams.VERTICAL_INSTALLATION);
    }
}
