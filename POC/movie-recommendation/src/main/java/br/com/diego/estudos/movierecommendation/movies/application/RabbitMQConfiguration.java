package br.com.diego.estudos.movierecommendation.movies.application;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfiguration {

    public static final String queueForSend = "ratingsForTraining";

    public static final String queueForReceive = "recommendations";


    @Bean
    Queue sendRatingsQueue() {
        return new Queue(queueForSend, false);
    }

    @Bean
    Queue receiveRecommendationsQueue() {
        return new Queue(queueForReceive, false);
    }

//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
////    @Bean
////    Binding bindingSendRatingsQueue(TopicExchange exchange) {
////        return BindingBuilder.bind(sendRatingsQueue()).to(exchange).with(routingKeyForSend);
////    }
////
////    @Bean
////    Binding bindingReceiveRecommendationsQueue(TopicExchange exchange) {
////        return BindingBuilder.bind(receiveRecommendationsQueue()).to(exchange).with(routingKeyForReceive);
////    }

//    @Bean
//    SimpleMessageListenerContainer recommendationContainer(ConnectionFactory connectionFactory,
//                                             @Qualifier("recommendationReceiverAdapter") MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueues(receiveRecommendationsQueue());
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

//    @Bean
//    SimpleMessageListenerContainer sendContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueues(sendRatingsQueue());
//        return container;
//    }
//
//    @Bean
//    MessageListenerAdapter recommendationReceiverAdapter(RecommendationReceiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveRecommendations");
//    }
}
