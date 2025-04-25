package com.pc_forge.backend.controller.filter;

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

    protected Boolean getBooleanFromRequestParam(String param) {
        return requestParameters.containsKey(param) ? Boolean.parseBoolean(requestParameters.get(param)[0]) : null;
    }

    protected Double getDoubleFromRequestParam(String param) {
        return requestParameters.containsKey(param) ? Double.parseDouble(requestParameters.get(param)[0]) : null;
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

    protected void splitJoinedParams() {
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

    public abstract Boolean empty();

    protected abstract void setFilter();
}
