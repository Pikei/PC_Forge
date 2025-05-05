package com.pc_forge.backend.controller.exceptions;

import lombok.Getter;

@Getter
public class UserNotVerifiedException extends Exception {
    private final Boolean newEmailSent;

    public UserNotVerifiedException(Boolean newEmailSent) {
        this.newEmailSent = newEmailSent;
    }
}
