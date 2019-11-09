package br.com.estudos.diego.JPA.pessoa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_PESSOA", discriminatorType = DiscriminatorType.STRING)
@Getter
@NoArgsConstructor
abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private LocalDate dataNascimento;

    private String cpf;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ElementCollection
    @CollectionTable(name = "telefones_pessoa", joinColumns = @JoinColumn(name = "codigo_pessoa",
            referencedColumnName = "codigo"))
    @Column(name = "numero_telefone")
    private List<String> telefones;


    @ElementCollection
    @CollectionTable(name = "contas_pessoa",
    joinColumns = @JoinColumn(name = "codigo_pessoa", referencedColumnName = "codigo"))
    @AttributeOverrides({
            @AttributeOverride(name = "numeroConta", column = @Column(name = "numero_conta"))
    })
    private List<Contas> contas;
}
