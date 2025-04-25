package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class DriveFilterResponse extends ProductFilterResponse {
    @JsonProperty(RequestParams.FORMAT)
    List<Object[]> format;

    @JsonProperty(RequestParams.INTERFACE)
    List<Object[]> driveInterface;

    @JsonProperty(RequestParams.STORAGE)
    List<Object[]> storage;
}
