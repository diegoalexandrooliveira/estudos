package br.com.diegoalexandrooliveira;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
class ProdutoExcluidoSQS {

    private final QueueMessagingTemplate template;

    ProdutoExcluidoSQS(AmazonSQSAsync amazonSQS) {
        this.template = new QueueMessagingTemplate(amazonSQS);
    }

    void enviar(Integer id) {
        String payload = "{\"id\": " + id + "}";
        template.send("produtoExcluido", MessageBuilder.withPayload(payload).build());
    }
}
