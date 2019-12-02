package com.example.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Run implements ApplicationRunner {


    @Autowired
    private IServiceA serviceA;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        serviceA.doIt("AAABBBCCC");



    }

//    @Bean
//    public String getPrefix() {
//        return "TEST";
//    }
}
