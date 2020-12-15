package diegoalexandrooliveira.designpatterns.impostos.template;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class ICPP extends TemplateImpostoCondicional {
    @Override
    protected double aplicaTaxacaoMinima(Orcamento orcamento) {
        return orcamento.getValor() * 0.05;
    }

    @Override
    protected double aplicaTaxacaoMaxima(Orcamento orcamento) {
        return orcamento.getValor() * 0.07;
    }

    @Override
    protected boolean ehTaxacaoMaxima(Orcamento orcamento) {
        return orcamento.getValor() >= 500;
    }
}
