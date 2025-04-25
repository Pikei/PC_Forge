package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class DriveFilter extends ProductFilter {
    protected List<String> selectedFormats;
    protected List<String> selectedInterfaces;
    protected List<Integer> selectedStorages;

    public DriveFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
    }

    protected Boolean checkCommonFiltersForDrives() {
        boolean result = checkCommonFilterFieldsIfEmpty();
        result = result && (selectedFormats == null || selectedFormats.isEmpty());
        result = result && (selectedInterfaces == null || selectedInterfaces.isEmpty());
        result = result && (selectedStorages == null || selectedStorages.isEmpty());
        return result;
    }

    protected void setCommonDriveFilters() {
        setCommonFilters();
        selectedFormats = getStringListFromRequestParam(RequestParams.FORMAT);
        selectedInterfaces = getStringListFromRequestParam(RequestParams.INTERFACE);
        selectedStorages = getIntegerListFromRequestParam(RequestParams.STORAGE);
    }
}
