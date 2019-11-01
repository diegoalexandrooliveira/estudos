package br.com.diego.estudos.movierecommendation.movies.rating.domain;

import br.com.diego.estudos.movierecommendation.movies.domain.Movie;
import br.com.diego.estudos.movierecommendation.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_imdb_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long rating;
}
