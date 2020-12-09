package diegoalexandrooliveira.designpatterns.impostos.strategy;


import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class ISS implements Imposto {

    public double calcula(Orcamento orcamento) {
        return orcamento.getValor() * 0.06;
    }
}
