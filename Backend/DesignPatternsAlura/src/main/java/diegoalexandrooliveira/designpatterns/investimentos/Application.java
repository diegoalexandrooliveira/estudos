package diegoalexandrooliveira.designpatterns.investimentos;

public class Application {


    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente(200D);

        Double lucro = RealizadorDeInvestimentos.investe(contaCorrente.getSaldo(), new Arrojado());

        contaCorrente.deposita(lucro);

        System.out.println(contaCorrente.getSaldo());
    }
}
