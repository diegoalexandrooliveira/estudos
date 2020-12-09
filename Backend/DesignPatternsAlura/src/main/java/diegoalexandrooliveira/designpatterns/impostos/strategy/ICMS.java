package diegoalexandrooliveira.designpatterns.impostos.strategy;


import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class ICMS implements Imposto {


    public double calcula(Orcamento orcamento) {
        return (orcamento.getValor() * 0.05) + 50;
    }
}
