package br.com.diegoalexandrooliveira;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
class Categoria {

    private final int id;

    private final String nome;

    public Categoria(String nome) {
        this.id = ThreadLocalRandom.current().nextInt(1000);
        this.nome = nome;
    }
}
