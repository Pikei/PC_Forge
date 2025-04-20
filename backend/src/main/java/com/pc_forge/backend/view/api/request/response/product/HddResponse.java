package com.pc_forge.backend.view.api.request.response.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HddResponse extends DriveResponse {
    private Integer rotationalSpeed;
}
