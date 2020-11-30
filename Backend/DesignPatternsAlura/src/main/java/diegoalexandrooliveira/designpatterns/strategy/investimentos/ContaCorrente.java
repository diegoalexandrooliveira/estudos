package diegoalexandrooliveira.designpatterns.strategy.investimentos;

public class ContaCorrente {

    private Double saldo;

    public Double getSaldo() {
        return saldo;
    }

    public ContaCorrente(Double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void deposita(Double valor) {
        this.saldo = this.saldo + valor;
    }
}
