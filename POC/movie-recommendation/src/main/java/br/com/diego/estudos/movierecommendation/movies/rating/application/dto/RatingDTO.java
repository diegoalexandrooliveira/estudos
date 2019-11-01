package br.com.diego.estudos.movierecommendation.movies.rating.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RatingDTO {

    private String imdb_id;

    private Long user_id;

    private Long rating;

}
