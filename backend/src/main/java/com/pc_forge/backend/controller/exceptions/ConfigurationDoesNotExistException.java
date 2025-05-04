package com.pc_forge.backend.controller.exceptions;

public class ConfigurationDoesNotExistException extends Exception {
    public ConfigurationDoesNotExistException(String message) {
        super(message);
    }
}
