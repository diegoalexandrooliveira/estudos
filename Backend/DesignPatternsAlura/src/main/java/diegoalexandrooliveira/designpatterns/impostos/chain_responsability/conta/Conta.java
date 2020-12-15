package diegoalexandrooliveira.designpatterns.impostos.chain_responsability.conta;

public class Conta {

    private String titular;

    private double saldo;

    public Conta(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
