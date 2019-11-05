package br.com.estudos.diego.JPA.carro.acessorio.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String descricao;
}
