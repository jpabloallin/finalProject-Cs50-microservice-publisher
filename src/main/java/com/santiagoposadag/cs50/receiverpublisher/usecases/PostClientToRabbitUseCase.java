package com.santiagoposadag.cs50.receiverpublisher.usecases;

import com.google.gson.Gson;
import com.santiagoposadag.cs50.receiverpublisher.config.RabbitMQConfig;
import com.santiagoposadag.cs50.receiverpublisher.dto.ClientDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class PostClientToRabbitUseCase implements Function<ClientDto, Mono<ClientDto>> {
    private final RabbitTemplate rabbitTemplate;
    private final Gson gson = new Gson();

    public PostClientToRabbitUseCase(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public Mono<ClientDto> apply(@Validated ClientDto dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, dto.getRoutingKey(), gson.toJson(dto));
        return Mono.just(dto);
    }
}
