package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class LiquidCoolerFilterResponse extends CoolerFilterResponse {
    @JsonProperty(RequestParams.COOLER_SIZE)
    private List<Object[]> coolerSize;
}
