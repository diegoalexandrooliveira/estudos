package diegoalexandrooliveira.designpatterns.impostos;

import diegoalexandrooliveira.designpatterns.impostos.chain_responsability.CalculadorDesconto;

public class Application {


    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(170);
        Item bola = new Item("Bola", 10);
        Item skate = new Item("Skate", 100);
        Item panela = new Item("Panela", 55);
        Item xicara = new Item("Xicara", 5);

        orcamento.adicionaItem(bola);
        orcamento.adicionaItem(skate);
        orcamento.adicionaItem(panela);
        orcamento.adicionaItem(xicara);

        CalculadorDesconto calculadorDesconto = new CalculadorDesconto();

        System.out.println(calculadorDesconto.calcula(orcamento));

    }
}
