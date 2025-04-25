package com.pc_forge.backend.view.response.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pc_forge.backend.controller.api.constants.RequestParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public final class HddFilterResponse extends DriveFilterResponse {
    @JsonProperty(RequestParams.ROTATIONAL_SPEED)
    List<Object[]> rotationalSpeed;
}
