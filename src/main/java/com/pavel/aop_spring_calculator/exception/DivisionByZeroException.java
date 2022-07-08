package com.pavel.aop_spring_calculator.exception;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
