package br.com.estudos.diego.JPA.pessoa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@SuperBuilder
@DiscriminatorValue("MOTORISTA")
public class Motorista extends Pessoa {

    private String numeroCNH;
}
