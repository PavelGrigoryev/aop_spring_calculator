package com.pavel.aop_spring_calculator.aspect;

import com.pavel.aop_spring_calculator.entity.Operation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect implements HealthIndicator {
    private final String path;

    public LoggingAspect(@Value("${aspect.path:/Temp}") String path) {
        this.path = path;
    }

    @AfterThrowing(pointcut = "com.pavel.aop_spring_calculator.aspect.Pointcuts.getResultMethod()", throwing = "e")
    public void afterThrowingGetResultMethodAdvice(RuntimeException e) {
        log.error(e.getMessage(), e);
    }

    @Around("com.pavel.aop_spring_calculator.aspect.Pointcuts.getResultMethod()")
    public Object aroundGetResultMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Мы входим в метод getResult()");

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        log.info("methodSignature = " + methodSignature);

        if (methodSignature.getName().equals("getResult")) {
            Object[] arguments = proceedingJoinPoint.getArgs();
            Arrays.stream(arguments).forEach(obj -> {
                if (obj instanceof Operation operation) {
                    log.info("Первая цифра - " + operation.getFirstNum() + ", Вторая цифра - "
                            + operation.getSecondNum() + ", Операция - " + operation.getOperation());
                } else {
                    log.info("Это не операция!");
                }
            });
        }

        Object target = proceedingJoinPoint.proceed();

        log.info("Мы вышли из метода getResult()");
        log.info("methodSignature = " + methodSignature);

        if (methodSignature.getName().equals("getResult")) {
            Object[] arguments = proceedingJoinPoint.getArgs();
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
        return target;
    }

    @Override
    public Health health() {
       try {
           File file = new File(path);
           if (file.exists()) {
               if (file.canWrite()) {
                   return Health.status("У нас всё зае**сь!").build();
               } else {
                   return Health.status("Всё упало!").build();
               }
           } else {
               return Health.status("Всё к **ям пошло!").build();
           }
       } catch (Exception ex) {
           return Health.down(ex).build();
       }
    }
}
