package com.inventory.exception;

public class DuplicateFabricException extends RuntimeException {
    public DuplicateFabricException(String message) {
        super(message);
    }
}