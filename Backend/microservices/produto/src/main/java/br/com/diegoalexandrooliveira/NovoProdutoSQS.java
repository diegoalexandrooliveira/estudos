package br.com.diegoalexandrooliveira;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
class NovoProdutoSQS {

    private final QueueMessagingTemplate template;

    NovoProdutoSQS(AmazonSQSAsync amazonSQS) {
        this.template = new QueueMessagingTemplate(amazonSQS);
    }

    void enviar(Produto produto) {
        String payload = "{\"id\": " + produto.getId() + "}";
        template.send("novoProduto", MessageBuilder.withPayload(payload).build());
    }
}
