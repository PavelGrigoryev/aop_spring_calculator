package com.pavel.aop_spring_calculator.service;

import com.pavel.aop_spring_calculator.entity.Operation;
import com.pavel.aop_spring_calculator.exception.DivisionByZeroException;
import com.pavel.aop_spring_calculator.exception.OperationNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private Double addition(String firstNum, String secondNum) {
        return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
    }

    private Double subtraction(String firsNum, String secondNum) {
        return Double.parseDouble(firsNum) - Double.parseDouble(secondNum);
    }

    private Double multiplication(String firstNum, String secondNum) {
        return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
    }

    private Double division(String firstNum, String secondNum) {
        if (Double.parseDouble(secondNum) != 0) {
            return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        } else {
            throw new DivisionByZeroException("you can't divide by zero");
        }
    }

    public void getResult(Operation operation) {
        switch (operation.getOperation()) {
            case "add" -> operation.setResult(addition(operation.getFirstNum(), operation.getSecondNum()));

            case "sub" -> operation.setResult(subtraction(operation.getFirstNum(), operation.getSecondNum()));

            case "mul" -> operation.setResult(multiplication(operation.getFirstNum(), operation.getSecondNum()));

            case "div" -> operation.setResult(division(operation.getFirstNum(), operation.getSecondNum()));

            default ->
                    throw new OperationNotFoundException("There is no such operation. There is only: add, sub, mul, div");
        }
    }
}
