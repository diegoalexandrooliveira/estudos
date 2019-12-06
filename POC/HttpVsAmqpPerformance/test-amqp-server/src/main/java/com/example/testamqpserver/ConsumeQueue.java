package com.example.testamqpserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsumeQueue {

    @Autowired
    private Repository repository;

    @RabbitListener(queues = "queue")
    public void receive(String json) throws JsonProcessingException {
        Model model = new ObjectMapper().readValue(json, Model.class);
        model.setCreated(LocalDateTime.now());
        repository.save(model);
    }

    @RabbitListener(queues = "queue")
    public void receive1(String json) throws JsonProcessingException {
        Model model = new ObjectMapper().readValue(json, Model.class);
        model.setCreated(LocalDateTime.now());
        repository.save(model);
    }
}
