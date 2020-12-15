package diegoalexandrooliveira.designpatterns.impostos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orcamento {

    private final double valor;
    private final List<Item> itens;


    public Orcamento(double valor) {
        this.valor = valor;
        itens = new ArrayList<>();
    }

    public void adicionaItem(Item item) {
        itens.add(item);
    }

    public double getValor() {
        return valor;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public boolean possuiItemPrecoMaiorQueCem() {
        return itens
                .stream()
                .anyMatch(item -> item.getValor() > 100);
    }
}
