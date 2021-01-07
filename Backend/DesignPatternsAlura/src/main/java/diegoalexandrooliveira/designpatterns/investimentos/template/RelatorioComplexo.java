package diegoalexandrooliveira.designpatterns.investimentos.template;

import diegoalexandrooliveira.designpatterns.investimentos.ContaCorrente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class RelatorioComplexo extends Relatorio {
    @Override
    protected String rodape() {
        return "atendente@bancojava.com.br | " + DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
    }

    @Override
    protected String corpo(List<ContaCorrente> contas) {
        return contas
                .stream()
                .map(conta -> "Titular: " + conta.getTitular() + " | Agência: " + conta.getAgencia() + " | Conta: " + conta.getNumeroConta() + " | Saldo: " + conta.getSaldo())
                .collect(Collectors.joining("\n"));
    }

    @Override
    protected String cabecalho() {
        return "Banco do Java | R do Java, 556 | 3564-4578";
    }
}
