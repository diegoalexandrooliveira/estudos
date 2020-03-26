package com.estudos.diego.moviesapi.movies.domain;

import com.estudos.diego.moviesapi.box_office.domain.BoxOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BoxOfficeService boxOfficeService;

    public List<Movie> getAll() {
        return movieRepository.findAll().stream()
                .map(movie -> {
                    movie.setBoxOffice(boxOfficeService.getByName(movie.getName()).orElse(null));
                    return movie;
                })
                .collect(Collectors.toList());
    }

    public Movie create(Movie movie) {
        return movieRepository.save(movie);
    }
}
