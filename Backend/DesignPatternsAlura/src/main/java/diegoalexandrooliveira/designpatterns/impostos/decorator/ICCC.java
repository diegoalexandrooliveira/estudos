package diegoalexandrooliveira.designpatterns.impostos.decorator;

import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ICCC extends Imposto {

    protected ICCC(Imposto outroImposto) {
        super(outroImposto);
    }

    public ICCC() {
        super(null);
    }

    @Override
    public double calcula(Orcamento orcamento) {
        return Limite
                .regraDo(orcamento.getValor())
                .calculo.apply(orcamento.getValor()) + this.calculaOutroImposto(orcamento);
    }


    private enum Limite {

        MENOR_1000(valor -> valor < 1000, valor -> valor * 0.05),
        ENTRE_1000_E_3000(valor -> valor >= 1000 && valor <= 3000, valor -> valor * 0.07),
        MAIOR_3000(valor -> valor > 3000, valor -> (valor * 0.08) + 30);

        static final List<Limite> limites = Arrays.asList(Limite.values());

        Predicate<Double> regra;
        UnaryOperator<Double> calculo;

        Limite(Predicate<Double> regra, UnaryOperator<Double> calculo) {
            this.regra = regra;
            this.calculo = calculo;
        }

        static Limite regraDo(Double valor) {
            return limites
                    .stream()
                    .filter(limite -> limite.regra.test(valor))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
