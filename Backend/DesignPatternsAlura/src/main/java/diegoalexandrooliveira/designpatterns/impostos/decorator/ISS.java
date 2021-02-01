package diegoalexandrooliveira.designpatterns.impostos.decorator;


import diegoalexandrooliveira.designpatterns.impostos.Orcamento;

public class ISS extends Imposto {

    protected ISS(Imposto outroImposto) {
        super(outroImposto);
    }

    public ISS() {
        super(null);
    }

    public double calcula(Orcamento orcamento) {
        return orcamento.getValor() * 0.06 + this.calculaOutroImposto(orcamento);
    }
}
