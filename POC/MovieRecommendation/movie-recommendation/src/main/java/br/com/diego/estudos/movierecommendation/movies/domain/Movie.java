package br.com.diego.estudos.movierecommendation.movies.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Entity(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Movie {

    @Id
    @JsonProperty(value = "imdbID")
    private String imdbId;

    @JsonProperty(value = "Title")
    private String title;

    @JsonProperty(value = "Year")
    private Long year;

    @JsonProperty(value = "Poster")
    private String urlPoster;

    @JsonProperty(value = "Metascore")
    private Long metascore;

    @JsonProperty(value = "Genre")
    private String genre;

}
