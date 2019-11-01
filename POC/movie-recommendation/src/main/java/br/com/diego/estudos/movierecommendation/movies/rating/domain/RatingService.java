package br.com.diego.estudos.movierecommendation.movies.rating.domain;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    private RabbitTemplate rabbitTemplate;


    public RatingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Rating addRating(Rating rating) throws Exception {

        if (Optional.ofNullable(rating).isEmpty() || Optional.ofNullable(rating.getRating()).isEmpty()
                || rating.getRating().compareTo(0L) <= 0 || rating.getRating().compareTo(5L) > 0) {
            throw new Exception("Invalid rate.");
        }


        Rating ratingFound = ratingRepository.findByMovieImdbIdAndUserId(rating.getMovie().getImdbId(), rating.getUser().getId());
        rating.setId(Optional.ofNullable(ratingFound).isEmpty() ? null : ratingFound.getId());

        rating = ratingRepository.save(rating);

        return rating;
    }


    public List<Rating> findByUserId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    public void deleteByUserId(Long userId) {
        ratingRepository.deleteByUserId(userId);
    }


}
