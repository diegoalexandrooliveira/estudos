package br.com.estudos.diego.JPA.carro.fabricante.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@AllArgsConstructor
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Alguns BD nao suportam,
//    @GeneratedValue(strategy = GenerationType.AUTO) // O JPA escolhe a melhor forma para o BD definido
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Sequence do banco, tem que definir o nome
//    @GeneratedValue(strategy = GenerationType.TABLE) // Cria uma nova tabela no BD e essa tabela armazena o valor da PK
    private Long codigo;

    private String nome;
}
