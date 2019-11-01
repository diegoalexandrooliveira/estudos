package br.com.diego.estudos.movierecommendation.movies.recommendation.domain;

import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {


    public List<Recommendation> findByUserId(Long userId);

    public Long deleteByUserId(Long userId);

    @Query("select r.movie from Recommendation r inner join r.movie " +
            " where r.user.id = :userId and (r.user.id, r.movie.imdbId)" +
            " not in (select ra.user.id, ra.movie.imdbId from Rating ra) and r.rating >= 3 ORDER BY r.rating desc")
    public List<Movie> findRecommendationsForUser(@Param("userId") Long userId);

}
