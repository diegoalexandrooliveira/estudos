package br.com.estudos.diego.JPA.aluguel.domain;

import br.com.estudos.diego.JPA.carro.domain.Carro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Builder
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@AllArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private BigDecimal valorTotal;

    @ManyToOne
    private Carro carro;

    @OneToOne(cascade = CascadeType.ALL)
    private ApoliceSeguro apoliceSeguro;

    @Temporal(TemporalType.DATE)
    private Calendar dataPedido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDevolucao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrega;
}
