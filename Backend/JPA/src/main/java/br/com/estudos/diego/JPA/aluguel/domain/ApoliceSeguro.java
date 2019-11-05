package br.com.estudos.diego.JPA.aluguel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apolice_seguro")
@EqualsAndHashCode(of = "codigo")
public class ApoliceSeguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private BigDecimal valorFranquia;
    private boolean protecaoTerceiro;
    private boolean protecaoCausasNaturais;
    private boolean protecaoRoubo;
}
