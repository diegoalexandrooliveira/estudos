package com.estudos.diego.SampleWebSocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@EnableScheduling
@EnableWebSocket
@EnableWebSocketMessageBroker
@EnableAutoConfiguration
@SpringBootApplication
public class SampleWebSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleWebSocketApplication.class, args);
	}

}
