package com.jaitechltd.webclientsspringbootexample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaitechltd.webclientsspringbootexample.config.properties.ExternalApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {

    public static final int BYTE_COUNT = 1024 * 1024 * 16;
    private final ExternalApiProperties externalApiProperties;

    public WebClientConfig(ExternalApiProperties externalApiProperties) {
        this.externalApiProperties = externalApiProperties;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean("postcodeIoWebClient")
    public WebClient postcodeIoWebClient() {
        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(BYTE_COUNT)).build();

        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-API-KEY", externalApiProperties.getPostCodeIoApi().getToken())
                .filter(logRequest())
                .filter(logResponse())
                .exchangeStrategies(exchangeStrategies)
                .baseUrl(externalApiProperties.getPostCodeIoApi().getBaseUrl())
                .build();
    }

    @Bean("starWarsWebClient")
    public WebClient starWarsWebClient() {
        log.debug("Creating Star Wars web client");

        final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(BYTE_COUNT)).build();

        return WebClient.builder()
                .filter(logRequest())
                .filter(logResponse())
                .exchangeStrategies(exchangeStrategies)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(externalApiProperties.getStarWarsApi().getBaseUrl())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Outgoing request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.debug("{}: {}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Incoming response: {} {}", clientResponse.statusCode(), clientResponse.statusCode().value());
            clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.debug("{}: {}", name, value)));
            return Mono.just(clientResponse);
        });
    }
}
