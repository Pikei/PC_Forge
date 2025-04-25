package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class HddFilter extends DriveFilter {
    private List<Integer> selectedRotationalSpeeds;

    public HddFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = checkCommonFiltersForDrives();
        result = result && (selectedRotationalSpeeds == null || selectedRotationalSpeeds.isEmpty());
        return result;
    }

    @Override
    public void setFilter() {
        setCommonDriveFilters();
        selectedRotationalSpeeds = getIntegerListFromRequestParam(RequestParams.ROTATIONAL_SPEED);
    }
}
