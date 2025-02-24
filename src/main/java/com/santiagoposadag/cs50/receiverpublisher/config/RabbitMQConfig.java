package com.santiagoposadag.cs50.receiverpublisher.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //nombres de colas
    public static final String GENERAL_QUEUE = "action.general";
    public static final String SELL_QUEUE = "action.sell";
    public static final String BUY_QUEUE = "action.buy";

    public static final String CLIENT_QUEUE = "client.create";


    //nombre del exchange
    public static final String EXCHANGE = "actions_exchange";


    //nombres de las claves de enrutamiento
    public static final String GENERAL_ROUTING_KEY = "routingKey.*";
    public static final String SELL_ROUTING_KEY = "routingKey.sell";
    public static final String BUY_ROUTING_KEY = "routingKey.buy";

    public static final String CLIENT_ROUTING_KEY = "clientKey.create";

    @Bean
    public Queue getGeneralQueue() {
        return new Queue(GENERAL_QUEUE);
    }

    @Bean
    public Queue getSellQueue() {
        return new Queue(SELL_QUEUE);
    }

    @Bean
    public Queue getBuyQueue() {
        return new Queue(BUY_QUEUE);
    }

    @Bean
    public Queue getClientQueue() {
        return new Queue(CLIENT_QUEUE);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding BindingToGeneralQueue() {
        return BindingBuilder.bind(getGeneralQueue()).to(getTopicExchange()).with(GENERAL_ROUTING_KEY);
    }

    @Bean
    public Binding BindingToSellQueue() {
        return BindingBuilder.bind(getSellQueue()).to(getTopicExchange()).with(SELL_ROUTING_KEY);
    }

    @Bean
    public Binding BindingToBuyQueue() {
        return BindingBuilder.bind(getBuyQueue()).to(getTopicExchange()).with(BUY_ROUTING_KEY);
    }

    @Bean
    public Binding BindingToClientQueue() {
        return BindingBuilder.bind(getClientQueue()).to(getTopicExchange()).with(CLIENT_ROUTING_KEY);
    }

}
