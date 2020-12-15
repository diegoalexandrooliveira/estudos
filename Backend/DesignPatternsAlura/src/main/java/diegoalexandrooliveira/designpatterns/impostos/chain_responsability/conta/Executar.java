package diegoalexandrooliveira.designpatterns.impostos.chain_responsability.conta;

public class Executar {


    public static void main(String[] args) {
        Requisicao requisicao = new Requisicao(Formato.PORCENTO);

        Conta conta = new Conta("Diego Alexandro de Oliveira", 1000);

        RespostaCSV respostaCSV = new RespostaCSV();
        RespostaPorcento respostaPorcento = new RespostaPorcento();
        RespostaXML respostaXML = new RespostaXML();

        respostaPorcento.setProximo(respostaCSV);
        respostaCSV.setProximo(respostaXML);

        respostaPorcento.formata(requisicao, conta);
    }
}
