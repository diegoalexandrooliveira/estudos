package diegoalexandrooliveira.designpatterns.impostos.chain_responsability.conta;

public class RespostaCSV implements Resposta {

    private Resposta proxima;

    @Override
    public void formata(Requisicao requisicao, Conta conta) {
        if (requisicao.getFormato() == Formato.CSV) {
            System.out.printf("%s,%s%n",
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
