package br.com.diegoalexandrooliveira.produto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Categoria {
    private int id;

    private String nome;
}
