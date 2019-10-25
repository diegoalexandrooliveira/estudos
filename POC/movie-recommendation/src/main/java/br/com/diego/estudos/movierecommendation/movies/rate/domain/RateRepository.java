package br.com.diego.estudos.movierecommendation.movies.rate.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    public List<Rate> findByUserId(Long userId);

    public Rate findByMovieImdbIdAndUserId(String movieImdbId, Long userId);

    @Transactional
    public void deleteByUserId(Long userId);
}
