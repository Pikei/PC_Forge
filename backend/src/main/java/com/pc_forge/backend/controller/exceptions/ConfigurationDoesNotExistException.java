package com.pc_forge.backend.controller.exceptions;

public class ConfigurationDoesNotExistException extends RuntimeException {
    public ConfigurationDoesNotExistException(String message) {
        super(message);
    }
}
