package br.com.diego.estudos.movierecommendation.movies.recommendation.application;


import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import br.com.diego.estudos.movierecommendation.movies.recommendation.domain.Recommendation;
import br.com.diego.estudos.movierecommendation.movies.recommendation.domain.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/recommendations"})
@CrossOrigin
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Movie> findRecommendationForUser(@PathVariable("userId") Long userId) {
        return recommendationService.findRecommendationForUser(userId);
    }

}
