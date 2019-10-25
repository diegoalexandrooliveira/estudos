package br.com.diego.estudos.movierecommendation.movies.rate.application;


import br.com.diego.estudos.movierecommendation.movies.rate.domain.Rate;
import br.com.diego.estudos.movierecommendation.movies.rate.domain.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/rates"})
@CrossOrigin
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping("/{userId}")
    public List<Rate> findRatesByUser(@PathVariable("userId") Long userId) {
        return rateService.findByUserId(userId);
    }

    @PostMapping
    public Rate addRate(@RequestBody Rate rate) throws Exception {
        return rateService.addRate(rate);
    }

    @DeleteMapping("/{userId}")
    public void deleteByUser(@PathVariable("userId") Long userId) {
        rateService.deleteByUserId(userId);
    }
}
