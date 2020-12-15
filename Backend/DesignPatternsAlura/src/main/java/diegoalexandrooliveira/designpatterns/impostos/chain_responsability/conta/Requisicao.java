package diegoalexandrooliveira.designpatterns.impostos.chain_responsability.conta;

public class Requisicao {

    private Formato formato;

    public Requisicao(Formato formato) {
        this.formato = formato;
    }

    public Formato getFormato() {
        return formato;
    }
}
