package com.example.spring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {


    @Around("@annotation(com.example.spring.aop.Logavel)")
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Chamou o metodo");

        proceedingJoinPoint.proceed();

        System.out.println("Terminou");
    }


}
