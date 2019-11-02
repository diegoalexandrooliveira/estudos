package br.com.diego.estudos.movierecommendation.movies.rating.application;


import br.com.diego.estudos.movierecommendation.movies.rating.application.dto.RatingDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/ratings"})
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingAppService ratingAppService;

    @GetMapping("/{userId}")
    public List<RatingDTO> findRatingsByUser(@PathVariable("userId") Long userId) {
        return ratingAppService.findByUserId(userId);
    }

    @PostMapping
    public RatingDTO addRating(@RequestBody RatingDTO ratingDTO) throws Exception {
        return ratingAppService.addRating(ratingDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable("userId") Long userId) throws JsonProcessingException {
        ratingAppService.deleteByUserId(userId);
    }
}
