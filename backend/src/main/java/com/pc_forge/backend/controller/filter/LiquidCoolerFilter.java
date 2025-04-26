package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class LiquidCoolerFilter extends CoolerFilter {
    private List<Integer> selectedCoolerSizes;

    public LiquidCoolerFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
    }

    @Override
    public Boolean empty() {
        boolean result = checkCommonCoolerFiltersIfEmpty();
        result = result && (selectedCoolerSizes == null || selectedCoolerSizes.isEmpty());
        return result;
    }

    @Override
    public void setFilter() {
        setCommonCoolerFilters();
        selectedCoolerSizes = getIntegerListFromRequestParam(RequestParams.COOLER_SIZE);
    }
}
