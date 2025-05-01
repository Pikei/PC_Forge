package com.pc_forge.backend.controller.exceptions;

public class ConfigurationAlreadyExists extends RuntimeException {
    public ConfigurationAlreadyExists(String message) {
        super(message);
    }
}
