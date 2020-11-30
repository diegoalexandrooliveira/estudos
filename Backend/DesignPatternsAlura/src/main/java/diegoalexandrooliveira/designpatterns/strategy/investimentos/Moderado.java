package diegoalexandrooliveira.designpatterns.strategy.investimentos;

public class Moderado implements Investimento {
    @Override
    public Double aplicaValor(Double valor) {
        boolean maior50Porcento = new java.util.Random().nextDouble() > 0.50;
        return maior50Porcento ? valor * 0.025 : valor * 0.007;
    }
}
