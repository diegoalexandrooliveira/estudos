package com.estudos.diego.SampleWebSocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatusScheduler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private int messageNumber;

    @Scheduled(fixedDelayString = "5000")
    public void exec() {
        simpMessagingTemplate.convertAndSend("statusApp", String.format("Enviando a mensagem número %s", ++messageNumber));
    }


}
