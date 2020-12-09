package diegoalexandrooliveira.designpatterns.impostos.chain_responsability;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class DescontoPorValor implements Desconto {


    private Desconto proximoDesconto;

    @Override
    public double desconta(Orcamento orcamento) {
        return orcamento.getValor() >= 500 ?
                orcamento.getValor() * 0.07 :
                proximoDesconto.desconta(orcamento);
    }

    @Override
    public void proximoDesconto(Desconto desconto) {
        this.proximoDesconto = desconto;
    }
}
