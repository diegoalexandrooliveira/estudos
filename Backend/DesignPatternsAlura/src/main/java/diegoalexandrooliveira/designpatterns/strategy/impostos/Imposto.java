package diegoalexandrooliveira.designpatterns.strategy.impostos;

import diegoalexandrooliveira.designpatterns.strategy.Orcamento;

public interface Imposto {

    double calcula(Orcamento orcamento);
}
