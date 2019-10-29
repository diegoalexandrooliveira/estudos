package br.com.diego.estudos.movierecommendation.movies.recommendation.application;

import br.com.diego.estudos.movierecommendation.movies.application.RabbitMQConfiguration;
import br.com.diego.estudos.movierecommendation.movies.recommendation.domain.Recommendation;
import br.com.diego.estudos.movierecommendation.movies.recommendation.domain.RecommendationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RecommendationReceiver {

    @Autowired
    private RecommendationService recommendationService;


    @RabbitListener(queues = RabbitMQConfiguration.queueForReceive)
    public void receiveRecommendations(@Payload String jsonRecommendations) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Recommendation> recommendations = Arrays.asList(objectMapper.readValue(jsonRecommendations, Recommendation[].class));
        recommendationService.receiveRecommendations(recommendations);

    }


}
