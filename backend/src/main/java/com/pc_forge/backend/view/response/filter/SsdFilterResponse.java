package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class SsdFilterResponse extends DriveFilterResponse {
    @JsonProperty(RequestParams.READ_SPEED)
    List<Object[]> readSpeed;

    @JsonProperty(RequestParams.WRITE_SPEED)
    List<Object[]> writeSpeed;
}
