package com.pc_forge.backend.controller.exceptions;

public class OrderDoesNotExistException extends Exception {
    public OrderDoesNotExistException(String message) {
        super(message);
    }
}
