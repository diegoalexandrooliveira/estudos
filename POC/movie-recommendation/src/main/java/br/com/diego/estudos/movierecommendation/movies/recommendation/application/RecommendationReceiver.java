package br.com.diego.estudos.movierecommendation.movies.recommendation.application;

import br.com.diego.estudos.movierecommendation.movies.application.RabbitMQConfiguration;
import br.com.diego.estudos.movierecommendation.movies.recommendation.application.dto.RecommendationAssembler;
import br.com.diego.estudos.movierecommendation.movies.recommendation.application.dto.RecommendationDTO;
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
import java.util.stream.Collectors;

@Component
public class RecommendationReceiver {

    @Autowired
    private RecommendationService recommendationService;


    @RabbitListener(queues = RabbitMQConfiguration.queueForReceive)
    public void receiveRecommendations(@Payload String jsonRecommendations) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<RecommendationDTO> recommendationsDTO = Arrays.asList(objectMapper.readValue(jsonRecommendations, RecommendationDTO[].class));
        List<Recommendation> recommendations = recommendationsDTO.stream().map(RecommendationAssembler::fromDTO).collect(Collectors.toList());
        recommendationService.receiveRecommendations(recommendations);
    }


}
