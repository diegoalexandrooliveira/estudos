package br.com.diego.estudos.movierecommendation.movies.rating.application;

import br.com.diego.estudos.movierecommendation.movies.application.RabbitMQConfiguration;
import br.com.diego.estudos.movierecommendation.movies.rating.application.dto.RatingAssembler;
import br.com.diego.estudos.movierecommendation.movies.rating.application.dto.RatingDTO;
import br.com.diego.estudos.movierecommendation.movies.rating.domain.Rating;
import br.com.diego.estudos.movierecommendation.movies.rating.domain.RatingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingAppService {

    @Autowired
    private RatingService ratingService;

    private RabbitTemplate rabbitTemplate;


    public RatingAppService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<RatingDTO> findByUserId(Long userId) {
        return ratingService.findByUserId(userId)
                .stream()
                .map(RatingAssembler::fromEntity)
                .collect(Collectors.toList());
    }

    public RatingDTO addRating(RatingDTO ratingDTO) throws Exception {

        Rating rating = RatingAssembler.fromDTO(ratingDTO);

        ratingDTO = RatingAssembler.fromEntity(ratingService.addRating(rating));

        sendRatingsToMessageBroker();

        return ratingDTO;

    }

    public void deleteByUserId(Long userId) throws JsonProcessingException {
        ratingService.deleteByUserId(userId);
        sendRatingsToMessageBroker();
    }

    public void sendRatingsToMessageBroker() throws JsonProcessingException {
        List<RatingDTO> ratingDTOS = ratingService.findAll().stream()
                .map(RatingAssembler::fromEntity).collect(Collectors.toList());

        String json = new ObjectMapper().writeValueAsString(ratingDTOS);

        rabbitTemplate.convertAndSend(RabbitMQConfiguration.queueForSend, json);
    }


}
