package br.com.diegoalexandrooliveira.estoque;

import br.com.diegoalexandrooliveira.produto.Produto;
import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Estoque {

    private final int id;

    private int quantidade = 1;

    private final Produto produto;

    public Estoque(@NonNull Produto produto) {
        this.id = ThreadLocalRandom.current().nextInt(1000);
        this.produto = produto;
    }
}
