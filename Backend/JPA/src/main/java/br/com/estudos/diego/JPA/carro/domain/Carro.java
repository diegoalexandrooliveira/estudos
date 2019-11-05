package br.com.estudos.diego.JPA.carro.domain;

import br.com.estudos.diego.JPA.aluguel.domain.Aluguel;
import br.com.estudos.diego.JPA.carro.acessorio.domain.Acessorio;
import br.com.estudos.diego.JPA.carro.modelo.domain.Modelo;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@EqualsAndHashCode(of = "codigo")
@Getter
@Setter
@Builder
@ToString(exclude = "acessorios")
@NoArgsConstructor
@AllArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String placa;

    private String cor;

    private BigDecimal valorDiaria;

    private String chassi;

    @ManyToOne
    @JoinColumn(name = "codigo_modelo")
    private Modelo modelo;

    @ManyToMany
    @JoinTable(name = "acessorio_carro")
    private List<Acessorio> acessorios;

    @OneToMany(mappedBy = "carro")
    private List<Aluguel> alugueis;

}
