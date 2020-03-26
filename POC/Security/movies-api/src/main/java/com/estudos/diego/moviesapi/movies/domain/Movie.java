package com.estudos.diego.moviesapi.movies.domain;


import com.estudos.diego.moviesapi.box_office.domain.BoxOffice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "movies")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Movie {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Transient
    @Setter
    private BoxOffice boxOffice;

}
