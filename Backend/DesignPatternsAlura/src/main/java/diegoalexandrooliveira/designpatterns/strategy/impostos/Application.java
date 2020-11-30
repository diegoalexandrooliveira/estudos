package diegoalexandrooliveira.designpatterns.strategy.impostos;

public class Application {


    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(3001);
        new CalculadorDeImposto().calculaImposto(orcamento, new ICMS());
        new CalculadorDeImposto().calculaImposto(orcamento, new ISS());
        new CalculadorDeImposto().calculaImposto(orcamento, new ICCC());
    }
}
