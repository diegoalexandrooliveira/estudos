package br.com.diego.estudos.movierecommendation.movies.application;


import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import br.com.diego.estudos.movierecommendation.movies.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping({"/movies"})
@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }


}
