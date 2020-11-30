package diegoalexandrooliveira.designpatterns.strategy.investimentos;

import java.util.Random;

public class Arrojado implements Investimento {
    @Override
    public Double aplicaValor(Double valor) {
        double chances = new Random().nextDouble();
        double chancesEntre30e50 = chances >= 0.21 && chances <= 0.5 ? valor * 0.03 :
                valor * 0.006;
        boolean chance20Porcento = chances >= 0 && chances <= 0.2;
        return chance20Porcento ? valor * 0.05 :
                chancesEntre30e50;
    }
}
