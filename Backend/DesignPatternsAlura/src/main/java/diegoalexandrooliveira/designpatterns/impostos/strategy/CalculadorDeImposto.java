package diegoalexandrooliveira.designpatterns.impostos.strategy;


import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class CalculadorDeImposto {

    public void calculaImposto(Orcamento orcamento, Imposto imposto) {
        System.out.println(imposto.calcula(orcamento));
    }
}
