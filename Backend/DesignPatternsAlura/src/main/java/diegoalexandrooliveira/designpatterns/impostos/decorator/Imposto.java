package diegoalexandrooliveira.designpatterns.impostos.decorator;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public abstract class Imposto {

    protected Imposto outroImposto;

    protected Imposto(Imposto outroImposto) {
        this.outroImposto = outroImposto;
    }

    public abstract double calcula(Orcamento orcamento);

    protected double calculaOutroImposto(Orcamento orcamento) {
        return outroImposto != null ? outroImposto.calcula(orcamento)
                : 0;
    }
}
