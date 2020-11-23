package com.example.AtmMachine.exception;

public class InsufficientResourcesException extends Exception {
    public InsufficientResourcesException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
