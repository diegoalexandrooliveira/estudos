package diegoalexandrooliveira.designpatterns.impostos.chain_responsability;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public interface Desconto {
    double desconta(Orcamento orcamento);
    void proximoDesconto(Desconto desconto);
}
