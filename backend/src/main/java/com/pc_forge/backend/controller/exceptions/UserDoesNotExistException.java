package com.pc_forge.backend.controller.exceptions;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
