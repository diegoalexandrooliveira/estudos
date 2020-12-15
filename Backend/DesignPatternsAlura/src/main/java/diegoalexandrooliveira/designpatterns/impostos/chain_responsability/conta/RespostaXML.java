package diegoalexandrooliveira.designpatterns.impostos.chain_responsability.conta;

public class RespostaXML implements Resposta {

    private Resposta proxima;

    @Override
    public void formata(Requisicao requisicao, Conta conta) {
        if (requisicao.getFormato() == Formato.XML) {
            System.out.printf("<conta><titular>%s</titular><saldo>%s</saldo></conta>%n",
                    conta.getTitular(), conta.getSaldo());
        } else {
            proxima.formata(requisicao, conta);
        }
    }

    @Override
    public void setProximo(Resposta resposta) {
        proxima = resposta;
    }
}
