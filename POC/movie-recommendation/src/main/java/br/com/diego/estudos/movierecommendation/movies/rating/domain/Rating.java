package br.com.diego.estudos.movierecommendation.movies.rating.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue
    @Setter
    @Getter
    private Long id;

    @Getter
    private String movieImdbId;

    @Getter
    private Long userId;

    @Getter
    private Long rating;
}
