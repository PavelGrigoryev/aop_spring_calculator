package com.pavel.aop_spring_calculator.controller;

import com.pavel.aop_spring_calculator.exception.DivisionByZeroException;
import com.pavel.aop_spring_calculator.exception.OperationNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DivisionByZeroException.class)
    public String divisionByZeroException(DivisionByZeroException divisionByZeroException, Model model) {
        model.addAttribute("message", divisionByZeroException.getMessage());
        return "error";
    }

    @ExceptionHandler(OperationNotFoundException.class)
    public String operationNotFoundException(OperationNotFoundException operationNotFoundException, Model model) {
        model.addAttribute("message", operationNotFoundException.getMessage());
        return "error";
    }
}
