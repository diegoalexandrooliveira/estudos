package com.example.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ServiceA implements IServiceA {

    @Autowired
    @Qualifier("serviceB")
    private IServiceB iServiceB;


    @Autowired
    @Nullable
    private String prefix;



    @Override
    @Logavel
    public void doIt(String text) {
        if(prefix == null){
            prefix = "123";
        }
        System.out.println(iServiceB.doSomething(String.format("%s.%s", prefix, text)));
    }
}
