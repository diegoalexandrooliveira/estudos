package br.com.diego.estudos.movierecommendation.movies.rating.application.dto;

import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import br.com.diego.estudos.movierecommendation.movies.rating.domain.Rating;
import br.com.diego.estudos.movierecommendation.movies.recommendation.domain.Recommendation;
import br.com.diego.estudos.movierecommendation.user.domain.User;

public class RatingAssembler {

    public static Rating fromDTO(RatingDTO ratingDTO) {
        return Rating.builder()
                .rating(ratingDTO.getRating())
                .movie(Movie.builder().imdbId(ratingDTO.getImdb_id()).build())
                .user(User.builder().id(ratingDTO.getUser_id()).build()).build();
    }

    public static RatingDTO fromEntity(Rating rating) {
        return RatingDTO.builder()
                .imdb_id(rating.getMovie().getImdbId())
                .user_id(rating.getUser().getId())
                .rating(rating.getRating())
                .build();
    }
}
