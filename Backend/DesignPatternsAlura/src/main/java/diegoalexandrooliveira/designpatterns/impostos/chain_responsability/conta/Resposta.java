package diegoalexandrooliveira.designpatterns.impostos.chain_responsability.conta;

public interface Resposta {

    void formata(Requisicao requisicao, Conta conta);

    void setProximo(Resposta resposta);
}
