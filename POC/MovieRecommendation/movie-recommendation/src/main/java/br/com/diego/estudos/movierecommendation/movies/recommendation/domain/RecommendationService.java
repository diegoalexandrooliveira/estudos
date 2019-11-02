package br.com.diego.estudos.movierecommendation.movies.recommendation.domain;

import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public void receiveRecommendations(List<Recommendation> recommendations) {
        recommendationRepository.deleteAll();
        recommendations.forEach(recommendationRepository::save);
    }

    public List<Movie> findRecommendationForUser(Long userId){
        return recommendationRepository.findRecommendationsForUser(userId);
    }


}
