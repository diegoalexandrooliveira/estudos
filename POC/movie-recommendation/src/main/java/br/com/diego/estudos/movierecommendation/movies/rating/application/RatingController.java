package br.com.diego.estudos.movierecommendation.movies.rating.application;


import br.com.diego.estudos.movierecommendation.movies.rating.domain.Rating;
import br.com.diego.estudos.movierecommendation.movies.rating.domain.RatingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/ratings"})
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{userId}")
    public List<Rating> findRatingsByUser(@PathVariable("userId") Long userId) {
        return ratingService.findByUserId(userId);
    }

    @PostMapping
    public Rating addRating(@RequestBody Rating rating) throws Exception {
        return ratingService.addRating(rating);
    }

    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable("userId") Long userId) throws JsonProcessingException {
        ratingService.deleteByUserId(userId);
    }
}
