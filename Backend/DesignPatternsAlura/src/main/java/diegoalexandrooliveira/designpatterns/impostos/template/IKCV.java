package diegoalexandrooliveira.designpatterns.impostos.template;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class IKCV extends TemplateImpostoCondicional {
    @Override
    protected double aplicaTaxacaoMinima(Orcamento orcamento) {
        return orcamento.getValor() * 0.06;
    }

    @Override
    protected double aplicaTaxacaoMaxima(Orcamento orcamento) {
        return orcamento.getValor() * 0.1;
    }

    @Override
    protected boolean ehTaxacaoMaxima(Orcamento orcamento) {
        return orcamento.getValor() > 500 && orcamento.possuiItemPrecoMaiorQueCem();
    }
}
