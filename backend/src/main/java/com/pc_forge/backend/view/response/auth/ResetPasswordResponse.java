package com.pc_forge.backend.view.response.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordResponse {
    private boolean success;
    private String error;
}
