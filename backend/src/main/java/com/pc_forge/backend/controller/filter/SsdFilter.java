package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class SsdFilter extends DriveFilter {
    private List<Integer> selectedWriteSpeeds;
    private List<Integer> selectedReadSpeeds;

    public SsdFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = checkCommonFiltersForDrives();
        result = result && (selectedWriteSpeeds == null || selectedWriteSpeeds.isEmpty());
        result = result && (selectedReadSpeeds == null || selectedReadSpeeds.isEmpty());
        return result;
    }

    @Override
    public void setFilter() {
        setCommonDriveFilters();
        selectedWriteSpeeds = getIntegerListFromRequestParam(RequestParams.WRITE_SPEED);
        selectedReadSpeeds = getIntegerListFromRequestParam(RequestParams.READ_SPEED);
    }
}
