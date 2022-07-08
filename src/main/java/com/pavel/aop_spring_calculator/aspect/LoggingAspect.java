package com.pavel.aop_spring_calculator.aspect;

import com.pavel.aop_spring_calculator.entity.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("com.pavel.aop_spring_calculator.aspect.Pointcuts.getResultMethod()")
    public void beforeGetResultMethod(JoinPoint joinPoint) {
        log.info("Мы входим в метод getResult()");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("methodSignature = " + methodSignature);

        if (methodSignature.getName().equals("getResult")) {
            Object[] arguments = joinPoint.getArgs();
            Arrays.stream(arguments).forEach(obj -> {
                if (obj instanceof Operation operation) {
                    log.info("Первая цифра - " + operation.getFirstNum() + ", Вторая цифра - "
                            + operation.getSecondNum() + ", Операция - " + operation.getOperation());
                } else {
                    log.info("Это не операция!");
                }
            });
        }
    }

    @After("com.pavel.aop_spring_calculator.aspect.Pointcuts.getResultMethod()")
    public void afterGetResultMethod(JoinPoint joinPoint) {
        log.info("Мы вышли из метода getResult()");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        log.info("methodSignature = " + methodSignature);

        if (methodSignature.getName().equals("getResult")) {
            Object[] arguments = joinPoint.getArgs();
            Arrays.stream(arguments).forEach(obj -> {
                if (obj instanceof Operation operation) {
                    log.info("Первая цифра - " + operation.getFirstNum() + ", Вторая цифра - "
                            + operation.getSecondNum() + ", Операция - " + operation.getOperation() +
                            ", Результат - " + operation.getResult());
                } else {
                    log.info("Это не операция!");
                }
            });
        }

        log.info("Я конечно такой себе АОПешник...");
    }
}
