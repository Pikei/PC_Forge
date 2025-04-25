package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class PsFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.PS_POWER)
    private List<Object[]> power;

    @JsonProperty(RequestParams.CERTIFICATE)
    private List<Object[]> efficiencyCertificate;

    @JsonProperty(RequestParams.EFFICIENCY)
    private List<Object[]> efficiency;

    @JsonProperty(RequestParams.PROTECTIONS)
    private List<Object[]> protections;

    @JsonProperty(RequestParams.COOLING_TYPE)
    private List<Object[]> coolingType;

    @JsonProperty(RequestParams.FAN_DIAMETER)
    private List<Object[]> fanDiameter;

    @JsonProperty(RequestParams.MODULAR_CABLING)
    private List<Object[]> modularCabling;

    @JsonProperty(RequestParams.LIGHTNING)
    private List<Object[]> lightning;
}
