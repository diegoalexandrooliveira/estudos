package br.com.diego.estudos.movierecommendation.movies.recommendation.application.dto;

import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import br.com.diego.estudos.movierecommendation.movies.recommendation.domain.Recommendation;
import br.com.diego.estudos.movierecommendation.user.domain.User;

public class RecommendationAssembler {

    public static Recommendation fromDTO(RecommendationDTO recommendationDTO) {
        return Recommendation.builder()
                .rating(recommendationDTO.getRating().longValue())
                .movie(Movie.builder().imdbId(recommendationDTO.getImdb_id()).build())
                .user(User.builder().id(recommendationDTO.getUser_id()).build()).build();
    }
}
