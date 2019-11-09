package br.com.estudos.diego.JPA.pessoa.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contas {

    private String numeroConta;

    private String banco;
}


