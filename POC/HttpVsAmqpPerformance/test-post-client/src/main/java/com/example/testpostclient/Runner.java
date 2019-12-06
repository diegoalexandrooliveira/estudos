package com.example.testpostclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@Component
public class Runner implements ApplicationRunner {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Iniciando envio para o servidor");
        LocalDateTime init = LocalDateTime.now();
        System.out.println(init);
        UnaryOperator<Integer> iterator = integer -> integer + 1;
        Stream.iterate(1, iterator).limit(10000).sequential().forEach(integer -> {
            Model model = Model.builder().description(String.format("Modelo %s", integer)).build();
            HttpEntity<Model> httpEntity = new HttpEntity<>(model);
            restTemplate.postForObject("http://localhost:8080/api/model", httpEntity, Model.class);
        });
        System.out.println("Terminado o envio 10000 registros");
        System.out.println(LocalDateTime.now());


    }
}
