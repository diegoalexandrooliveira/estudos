package br.com.estudos.diego.JPA.carro.modelo.domain;

import br.com.estudos.diego.JPA.carro.fabricante.domain.Fabricante;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@AllArgsConstructor
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String descricao;

    @ManyToOne
    private Fabricante fabricante;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
