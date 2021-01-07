package diegoalexandrooliveira.designpatterns.investimentos.template;

import diegoalexandrooliveira.designpatterns.investimentos.ContaCorrente;

import java.util.List;

public class Application {


    public static void main(String[] args) {
        ContaCorrente diego = new ContaCorrente(100D, "Diego", "1234", "546856");
        ContaCorrente joao = new ContaCorrente(54650D, "João", "1234", "546880");
        ContaCorrente camila = new ContaCorrente(985D, "Camila", "1234", "785465");

        List<ContaCorrente> contas = List.of(diego, joao, camila);

        System.out.println(new RelatorioComplexo().imprimir(contas));
    }
}
