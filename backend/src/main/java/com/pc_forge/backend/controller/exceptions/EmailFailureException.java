package com.pc_forge.backend.controller.exceptions;

public class EmailFailureException extends Exception {
    public EmailFailureException(String message) {
        super(message);
    }
}
