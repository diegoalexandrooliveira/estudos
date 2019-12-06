package com.example.testamqpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@Component
public class Runner implements ApplicationRunner {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Iniciando envio para o servidor");
        LocalDateTime init = LocalDateTime.now();
        System.out.println(init);
        UnaryOperator<Integer> iterator = integer -> integer + 1;
        Stream.iterate(1, iterator).limit(100000).sequential().forEach(integer -> {
            Model model = Model.builder().description(String.format("Modelo %s", integer)).build();
            try {
                rabbitTemplate.convertAndSend("amq.direct", "amq.direct", new ObjectMapper().writeValueAsString(model));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Terminado o envio 100000 registros");
        System.out.println(LocalDateTime.now());


    }
}
