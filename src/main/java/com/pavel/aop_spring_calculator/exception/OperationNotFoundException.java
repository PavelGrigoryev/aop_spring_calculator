package com.pavel.aop_spring_calculator.exception;

public class OperationNotFoundException extends RuntimeException {
    public OperationNotFoundException(String message) {
        super(message);
    }
}
