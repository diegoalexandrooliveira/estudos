package diegoalexandrooliveira.designpatterns.investimentos.template;

import diegoalexandrooliveira.designpatterns.investimentos.ContaCorrente;

import java.util.List;

public abstract class Relatorio {


    public final String imprimir(List<ContaCorrente> contas) {
        String cabecalho = cabecalho();

        String corpo = corpo(contas);

        String rodape = rodape();

        return cabecalho + "\n" + corpo + "\n" + rodape;
    }

    protected abstract String rodape();

    protected abstract String corpo(List<ContaCorrente> contas);

    protected abstract String cabecalho();
}
