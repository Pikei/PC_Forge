package com.pc_forge.backend.view.response.auth;

import lombok.Data;

@Data
public class LoginResponse {
    private String jwt;
    private Boolean success;
    private String error;
}
