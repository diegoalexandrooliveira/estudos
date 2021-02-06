package br.com.diegoalexandrooliveira;

import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

@Getter
class Produto {

    private final int id;

    private final String nome;

    private final Categoria categoria;

    public Produto(String nome, @NonNull Categoria categoria) {
        this.id = ThreadLocalRandom.current().nextInt(1000);
        this.nome = nome;
        this.categoria = categoria;
    }
}
