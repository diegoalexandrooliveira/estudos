package br.com.diego.estudos.movierecommendation.movies.recommendation.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendationDTO {

    private String imdb_id;

    private Long user_id;

    private BigDecimal rating;

}
