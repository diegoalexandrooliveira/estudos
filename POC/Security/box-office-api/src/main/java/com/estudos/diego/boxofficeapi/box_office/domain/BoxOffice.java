package com.estudos.diego.boxofficeapi.box_office.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "box_office")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoxOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movieName;

    private BigDecimal tickets;

}
