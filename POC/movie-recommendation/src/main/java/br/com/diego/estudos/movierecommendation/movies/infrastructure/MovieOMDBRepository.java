package br.com.diego.estudos.movierecommendation.movies.infrastructure;

import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class MovieOMDBRepository {


    @Value("${movie-recommendation.omdbapi.url}")
    private String URL;

    @Value("${movie-recommendation.omdbapi.key}")
    private String KEY;


    public Optional<Movie> findMovieById(String imdb_id) {
        return Optional.of(new RestTemplate().getForObject(URL + "?i=" + imdb_id + "&apikey=" + KEY, Movie.class));
    }
}
