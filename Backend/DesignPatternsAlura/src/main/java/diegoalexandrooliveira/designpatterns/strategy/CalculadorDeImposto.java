package diegoalexandrooliveira.designpatterns.strategy;


import diegoalexandrooliveira.designpatterns.strategy.impostos.Imposto;

public class CalculadorDeImposto {

    public void calculaImposto(Orcamento orcamento, Imposto imposto) {
        System.out.println(imposto.calcula(orcamento));
    }
}
