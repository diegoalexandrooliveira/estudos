package com.example.spring.function;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.UnaryOperator;

@Component
public class Run implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {


        Animais gato = Animais.GATO;
        Animais cachorro = Animais.CACHORRO;
        Animais vaca = Animais.VACA;

        List<Animais> animais = List.of(gato, cachorro, vaca);

        animais.forEach(animal -> System.out.println(animal.getBarulho().get()));


        UnaryOperator<String> func1 = (s) -> s.concat("123");
        UnaryOperator<String> func2 = (s) -> s.concat("321");

        System.out.println(func1.andThen(func2).apply("teste "));


    }
}
