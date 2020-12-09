package diegoalexandrooliveira.designpatterns.impostos.chain_responsability;

import diegoalexandrooliveira.designpatterns.impostos.Item;
import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

import java.util.List;

public class DescontoVendaCasada implements Desconto {

    private Desconto desconto;

    private List<Item> itensNecessarios = List.of(
            new Item("Xicara", 0),
            new Item("Panela", 0)
    );

    @Override
    public double desconta(Orcamento orcamento) {
        return orcamento.getItens().containsAll(itensNecessarios) ?
                orcamento.getValor() * 0.05 :
                0;
    }

    @Override
    public void proximoDesconto(Desconto desconto) {
        this.desconto = desconto;
    }
}
