package com.pavel.aop_spring_calculator.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Operation {

    @Pattern(regexp = "\\d+([.]\\d+)?", message = "You can only enter whole and fractional numbers")
    private String firstNum;

    @Pattern(regexp = "\\d+([.]\\d+)?", message = "You can only enter whole and fractional numbers")
    private String secondNum;

    @Pattern(regexp = "[A-z]{3}", message = "The name for the action must be three letters long: add, sub, mul, div")
    private String operation;

    private Double result;
}
