package com.estudos.diego.moviesapi.movies.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;


    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }
}
