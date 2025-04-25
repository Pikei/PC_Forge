package com.pc_forge.backend.controller.filter;

import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public final class PowerSupplyFilter extends ProductFilter {
    private List<Integer> selectedPowers;
    private List<String> selectedCertificates;
    private List<Integer> selectedEfficiencies;
    private List<String> selectedCoolingTypes;
    private List<Integer> selectedFanDiameters;
    private List<String> selectedProtections;
    private Boolean modularCabling;
    private Boolean lightning;


    public PowerSupplyFilter(Map<String, String[]> requestParameters) {
        super(requestParameters);
        setFilter();
    }

    @Override
    public Boolean empty() {
        boolean result = getPriceMaximum() == null && getPriceMinimum() == null;
        result = result && (getSelectedProducers() == null || getSelectedProducers().isEmpty());
        result = result && (selectedPowers == null || selectedPowers.isEmpty());
        result = result && (selectedCertificates == null || selectedCertificates.isEmpty());
        result = result && (selectedEfficiencies == null || selectedEfficiencies.isEmpty());
        result = result && (selectedCoolingTypes == null || selectedCoolingTypes.isEmpty());
        result = result && (selectedFanDiameters == null || selectedFanDiameters.isEmpty());
        result = result && (selectedProtections == null || selectedProtections.isEmpty());
        result = result && (modularCabling == null);
        result = result && (lightning == null);
        return result;
    }

    @Override
    protected void setFilter() {
        splitJoinedParams();
        setPriceMinimum(getDoubleFromRequestParam(RequestParams.PRICE_MINIMUM));
        setPriceMaximum(getDoubleFromRequestParam(RequestParams.PRICE_MAXIMUM));
        setSelectedProducers(getStringListFromRequestParam(RequestParams.PRODUCER));
        selectedPowers = getIntegerListFromRequestParam(RequestParams.PS_POWER);
        selectedCertificates = getStringListFromRequestParam(RequestParams.CERTIFICATE);
        selectedEfficiencies = getIntegerListFromRequestParam(RequestParams.EFFICIENCY);
        selectedCoolingTypes = getStringListFromRequestParam(RequestParams.COOLING_TYPE);
        selectedFanDiameters = getIntegerListFromRequestParam(RequestParams.FAN_DIAMETER);
        selectedProtections = getStringListFromRequestParam(RequestParams.PROTECTIONS);
        modularCabling = getBooleanFromRequestParam(RequestParams.MODULAR_CABLING);
        lightning = getBooleanFromRequestParam(RequestParams.LIGHTNING);
    }
}
