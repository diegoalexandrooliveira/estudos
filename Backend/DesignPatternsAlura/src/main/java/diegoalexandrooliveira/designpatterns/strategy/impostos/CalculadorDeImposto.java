package diegoalexandrooliveira.designpatterns.strategy.impostos;


public class CalculadorDeImposto {

    public void calculaImposto(Orcamento orcamento, Imposto imposto) {
        System.out.println(imposto.calcula(orcamento));
    }
}
