package diegoalexandrooliveira.designpatterns.impostos.decorator;


import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class ICMS extends Imposto {
    protected ICMS(Imposto outroImposto) {
        super(outroImposto);
    }

    public ICMS() {
        super(null);
    }

    public double calcula(Orcamento orcamento) {
        return ((orcamento.getValor() * 0.05) + 50) + +this.calculaOutroImposto(orcamento);
    }
}
