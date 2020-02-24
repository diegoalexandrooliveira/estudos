package com.estudos.diego.moviesapi.movies.application;

import com.estudos.diego.moviesapi.movies.domain.Movie;
import com.estudos.diego.moviesapi.movies.domain.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping
    public HttpEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @PostMapping
    public HttpEntity<Movie> post(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.create(movie));
    }
}
