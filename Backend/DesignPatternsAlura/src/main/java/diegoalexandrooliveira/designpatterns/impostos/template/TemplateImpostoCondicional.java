package diegoalexandrooliveira.designpatterns.impostos.template;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;
import diegoalexandrooliveira.designpatterns.impostos.strategy.Imposto;

public abstract class TemplateImpostoCondicional implements Imposto {

    @Override
    public double calcula(Orcamento orcamento) {
        if (ehTaxacaoMaxima(orcamento)) {
            return aplicaTaxacaoMaxima(orcamento);
        } else {
            return aplicaTaxacaoMinima(orcamento);
        }
    }

    protected abstract double aplicaTaxacaoMinima(Orcamento orcamento);

    protected abstract double aplicaTaxacaoMaxima(Orcamento orcamento);

    protected abstract boolean ehTaxacaoMaxima(Orcamento orcamento);
}
