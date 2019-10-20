package br.com.diego.estudos.movierecommendation.movies.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Year;

@Entity(name = "movies")
public class Movie {

    @Id
    private String imdbId;

    private String title;

    private Year year;

    private String posterUrl;

    private Long metascore;

}
