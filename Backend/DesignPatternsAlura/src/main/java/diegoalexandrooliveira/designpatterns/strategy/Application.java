package diegoalexandrooliveira.designpatterns.strategy;

import diegoalexandrooliveira.designpatterns.strategy.impostos.ICCC;
import diegoalexandrooliveira.designpatterns.strategy.impostos.ICMS;
import diegoalexandrooliveira.designpatterns.strategy.impostos.ISS;

public class Application {


    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(3001);
        new CalculadorDeImposto().calculaImposto(orcamento, new ICMS());
        new CalculadorDeImposto().calculaImposto(orcamento, new ISS());
        new CalculadorDeImposto().calculaImposto(orcamento, new ICCC());
    }
}
