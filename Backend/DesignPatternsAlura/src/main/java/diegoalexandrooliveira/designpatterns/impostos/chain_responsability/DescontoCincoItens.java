package diegoalexandrooliveira.designpatterns.impostos.chain_responsability;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class DescontoCincoItens implements Desconto {


    private Desconto proximoDesconto;

    @Override
    public double desconta(Orcamento orcamento) {
        return orcamento.getItens().size() >= 5 ?
                orcamento.getValor() * 0.1 :
                proximoDesconto.desconta(orcamento);
    }

    @Override
    public void proximoDesconto(Desconto desconto) {
        this.proximoDesconto = desconto;
    }
}
