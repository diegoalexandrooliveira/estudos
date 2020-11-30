package diegoalexandrooliveira.designpatterns.strategy.investimentos;

public class RealizadorDeInvestimentos {

    private RealizadorDeInvestimentos(){
        
    }

    public static Double investe(Double valor, Investimento investimento) {

        Double lucroInvestido = investimento.aplicaValor(valor);

        return lucroInvestido * 0.75;
    }
}
