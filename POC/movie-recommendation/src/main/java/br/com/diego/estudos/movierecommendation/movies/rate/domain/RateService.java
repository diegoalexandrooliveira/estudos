package br.com.diego.estudos.movierecommendation.movies.rate.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public Rate addRate(Rate rate) throws Exception {

        if (Optional.ofNullable(rate).isEmpty() || Optional.ofNullable(rate.getRate()).isEmpty()
                || rate.getRate().compareTo(0L) <= 0 || rate.getRate().compareTo(5L) > 0) {
            throw new Exception("Invalid rate.");
        }

        Rate rateFound = rateRepository.findByMovieImdbIdAndUserId(rate.getMovieImdbId(), rate.getUserId());
        rate.setId(Optional.ofNullable(rateFound).isEmpty() ? null : rateFound.getId());
        return rateRepository.save(rate);
    }


    public List<Rate> findByUserId(Long userId) {
        return rateRepository.findByUserId(userId);
    }

    public void deleteByUserId(Long userId) {
        rateRepository.deleteByUserId(userId);
    }
}
