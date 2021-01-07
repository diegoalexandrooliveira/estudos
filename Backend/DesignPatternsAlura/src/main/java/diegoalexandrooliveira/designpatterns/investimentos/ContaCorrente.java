package diegoalexandrooliveira.designpatterns.investimentos;

public class ContaCorrente {

    private Double saldo;

    private String titular;

    private String agencia;

    private String numeroConta;

    public Double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public ContaCorrente(Double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public ContaCorrente(Double saldo, String titular, String agencia, String numeroConta) {
        this.saldo = saldo;
        this.titular = titular;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
    }

    public void deposita(Double valor) {
        this.saldo = this.saldo + valor;
    }
}
