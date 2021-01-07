package diegoalexandrooliveira.designpatterns.investimentos.template;

import diegoalexandrooliveira.designpatterns.investimentos.ContaCorrente;

import java.util.List;
import java.util.stream.Collectors;

public class RelatorioSimples extends Relatorio {
    @Override
    protected String rodape() {
        return "Telefone: 3215-5465";
    }

    @Override
    protected String corpo(List<ContaCorrente> contas) {
        return contas
                .stream()
                .map(conta -> "Titular: " + conta.getTitular() + " | " + "Saldo: " + conta.getSaldo())
                .collect(Collectors.joining("\n"));
    }

    @Override
    protected String cabecalho() {
        return "Banco do Java";
    }
}
