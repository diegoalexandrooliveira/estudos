package diegoalexandrooliveira.designpatterns.impostos.strategy;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public interface Imposto {

    double calcula(Orcamento orcamento);
}
