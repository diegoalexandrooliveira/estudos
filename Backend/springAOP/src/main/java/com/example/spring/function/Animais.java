package com.example.spring.function;

import java.util.function.Supplier;


public enum Animais {

    GATO(() -> "Miau"),
    CACHORRO(() -> "Auau"),
    VACA(() -> "Muuuuu");


    Animais(Supplier barulho) {
        this.barulho = barulho;
    }


    private Supplier<String> barulho;

    public Supplier<String> getBarulho(){
        return this.barulho;
    }


}
