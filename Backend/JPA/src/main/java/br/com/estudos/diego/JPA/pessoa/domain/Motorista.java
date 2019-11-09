package br.com.estudos.diego.JPA.pessoa.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@SuperBuilder
@DiscriminatorValue("MOTORISTA")
@NoArgsConstructor
public class Motorista extends Pessoa {

    private String numeroCNH;
}
