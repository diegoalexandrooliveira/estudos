package br.com.diego.estudos.movierecommendation.movies.recommendation.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    public List<Recommendation> findByUserId(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    public void receiveRecommendations(List<Recommendation> recommendations) {
        recommendationRepository.deleteAll();
        recommendations.forEach(recommendationRepository::save);
    }


}
