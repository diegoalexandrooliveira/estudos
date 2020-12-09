package diegoalexandrooliveira.designpatterns.impostos.chain_responsability;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class CalculadorDesconto {


    public double calcula(Orcamento orcamento) {
        DescontoCincoItens descontoCincoItens = new DescontoCincoItens();
        DescontoPorValor descontoPorValor = new DescontoPorValor();
        DescontoVendaCasada descontoVendaCasada = new DescontoVendaCasada();
        SemDesconto semDesconto = new SemDesconto();

        descontoCincoItens.proximoDesconto(descontoPorValor);
        descontoPorValor.proximoDesconto(descontoVendaCasada);
        descontoVendaCasada.proximoDesconto(semDesconto);

        return descontoCincoItens.desconta(orcamento);
    }
}
