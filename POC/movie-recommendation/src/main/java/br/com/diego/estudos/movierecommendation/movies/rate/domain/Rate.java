package br.com.diego.estudos.movierecommendation.movies.rate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rate {

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
    private Long rate;
}
