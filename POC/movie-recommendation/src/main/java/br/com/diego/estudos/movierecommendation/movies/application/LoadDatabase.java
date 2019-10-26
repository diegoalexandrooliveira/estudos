package br.com.diego.estudos.movierecommendation.movies.application;

import br.com.diego.estudos.movierecommendation.movies.domain.MovieRepository;
import br.com.diego.estudos.movierecommendation.movies.infrastructure.MovieOMDBRepository;
import br.com.diego.estudos.movierecommendation.movies.infrastructure.MoviesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieOMDBRepository omdbRepository;

    @Override
    public void run(String... args) throws Exception {
        Arrays.asList(MoviesList.values())
                .stream()
                .map(MoviesList::getImdb_id)
                .filter(imdb_id -> movieRepository.findById(imdb_id).isEmpty())
                .map(omdbRepository::findMovieById)
                .forEach(optionalMovie -> optionalMovie.ifPresent(movieRepository::save));
    }
}
