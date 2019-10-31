package br.com.diego.estudos.movierecommendation.movies.rating.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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
