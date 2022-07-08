package com.pavel.aop_spring_calculator.controller;

import com.pavel.aop_spring_calculator.entity.Operation;
import com.pavel.aop_spring_calculator.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculator")
    public ModelAndView getCalculatePage(Operation operation, ModelAndView modelAndView) {
        modelAndView.setViewName("calculator");
        return modelAndView;
    }

    @PostMapping("/calculate")
    public ModelAndView postCalculatePage(@Valid Operation operation, Errors errors, ModelAndView modelAndView) {
        if (!errors.hasErrors()) {
            calculatorService.getResult(operation);
            modelAndView.addObject("operation", operation);
        }
        modelAndView.setViewName("calculator");
        return modelAndView;
    }
}
