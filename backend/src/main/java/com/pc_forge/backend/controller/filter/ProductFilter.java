package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public abstract class ProductFilter {
    private Double priceMinimum;
    private Double priceMaximum;
    private List<String> selectedProducers;
    protected Map<String, String[]> requestParameters;

    public ProductFilter(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public abstract Boolean empty();

    public abstract void setFilter();

    protected Boolean checkCommonFilterFieldsIfEmpty() {
        boolean result = priceMinimum == null && priceMaximum == null;
        result = result && (selectedProducers == null || selectedProducers.isEmpty());
        return result;
    }

    protected void setCommonFilters() {
        splitJoinedParams();
        setPriceMinimum(getDoubleFromRequestParam(RequestParams.PRICE_MINIMUM));
        setPriceMaximum(getDoubleFromRequestParam(RequestParams.PRICE_MAXIMUM));
        setSelectedProducers(getStringListFromRequestParam(RequestParams.PRODUCER));
    }

    private void splitJoinedParams() {
        Map<String, String[]> params = new HashMap<>();
        for (String key : requestParameters.keySet()) {
            if (requestParameters.get(key).length == 1) {
                params.put(key, requestParameters.get(key)[0].split(","));
            } else {
                params.put(key, requestParameters.get(key));
            }
        }
        this.requestParameters = params;
    }

    protected Boolean getBooleanFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            try {
                return Boolean.parseBoolean(requestParameters.get(param)[0]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    protected Integer getIntegerFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            try {
                return Integer.parseInt(requestParameters.get(param)[0]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    protected Double getDoubleFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            try {
                return Double.parseDouble(requestParameters.get(param)[0]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    protected List<String> getStringListFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            return Arrays.stream(requestParameters.get(param)).toList();
        }
        return null;
    }

    protected List<Integer> getIntegerListFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            return Arrays.stream(requestParameters.get(param)).map(Integer::valueOf).toList();
        }
        return null;
    }

    protected List<Double> getDoubleListFromRequestParam(String param) {
        if (requestParameters.containsKey(param)) {
            return Arrays.stream(requestParameters.get(param)).map(Double::valueOf).toList();
        }
        return null;
    }
}
