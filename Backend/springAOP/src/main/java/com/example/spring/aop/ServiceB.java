package com.example.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class ServiceB implements IServiceB {
    @Override
    public String doSomething(String something) {
        return something.toLowerCase();
    }
}
