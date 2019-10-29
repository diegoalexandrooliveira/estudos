package br.com.diego.estudos.movierecommendation.movies.recommendation.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {


    public List<Recommendation> findByUserId(Long userId);

    public Long deleteByUserId(Long userId);

}
