package diegoalexandrooliveira.designpatterns.strategy.investimentos;

public class Conservador implements Investimento {
    @Override
    public Double aplicaValor(Double valor) {
        return valor * 0.008;
    }
}
