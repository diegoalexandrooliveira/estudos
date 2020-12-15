package diegoalexandrooliveira.designpatterns.impostos;

import diegoalexandrooliveira.designpatterns.impostos.strategy.CalculadorDeImposto;
import diegoalexandrooliveira.designpatterns.impostos.template.ICPP;

public class Application {


    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(400);
        Item bola = new Item("Bola", 10);
        Item skate = new Item("Skate", 150);
        Item panela = new Item("Panela", 55);
        Item xicara = new Item("Xicara", 5);

        orcamento.adicionaItem(bola);
        orcamento.adicionaItem(skate);
        orcamento.adicionaItem(panela);
        orcamento.adicionaItem(xicara);

        CalculadorDeImposto calculadorDeImposto = new CalculadorDeImposto();

        calculadorDeImposto.calculaImposto(orcamento, new ICPP());


    }
}
