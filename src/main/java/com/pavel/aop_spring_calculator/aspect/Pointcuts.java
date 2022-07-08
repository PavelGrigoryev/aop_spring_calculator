package com.pavel.aop_spring_calculator.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.pavel.aop_spring_calculator.service.CalculatorService.getResult(..))")
    public void getResultMethod() {
    }
}
