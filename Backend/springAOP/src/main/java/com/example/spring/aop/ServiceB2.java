package com.example.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class ServiceB2 implements IServiceB {
    @Override
    public String doSomething(String something) {
        return something;
    }
}
