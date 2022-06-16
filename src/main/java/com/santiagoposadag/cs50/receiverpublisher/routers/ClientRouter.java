package com.santiagoposadag.cs50.receiverpublisher.routers;

import com.santiagoposadag.cs50.receiverpublisher.dto.ClientDto;
import com.santiagoposadag.cs50.receiverpublisher.usecases.PostClientToRabbitUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ClientRouter {


    @Bean
    public RouterFunction<ServerResponse> postClientRoute(PostClientToRabbitUseCase useCase){
        Function<ClientDto, Mono<ServerResponse>> executor =
                clientDTO -> useCase.apply(clientDTO)
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result));

        return route(POST("/client")
                        .and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ClientDto.class)
                        .flatMap(executor));

    }
}
