package br.com.diego.estudos.movierecommendation.movies.rating.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    public List<Rating> findByUserId(Long userId);

    public Rating findByMovieImdbIdAndUserId(String movieImdbId, Long userId);

    @Transactional
    public void deleteByUserId(Long userId);
}
