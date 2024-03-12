package com.jaitechltd.webclientsspringbootexample.webclient;

import com.jaitechltd.webclientsspringbootexample.dto.netify.FilmResponseDto;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.jaitechltd.webclientsspringbootexample.utils.GraphQLQueryLoader.loadQueryFromFile;

@Slf4j
@Component
public class StarWarsClient {

    protected WebClient webClient;

    public StarWarsClient(@Qualifier("starWarsWebClient") final WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Get all films from Star Wars API using GraphQL
     *
     * @return FilmResponseDto see {@link FilmResponseDto}
     */
    @Timed(histogram = true)
    public FilmResponseDto getAllFilms() {
        String query;
        try {
            query = loadQueryFromFile("graphqlQuerys/all-films-query.graphql");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load GraphQL query from file", e);
        }

        Map<String, Object> variables = new HashMap<>();
        //TODO: Add variables if needed

        log.info("Calling Star Wars external API to get all films wth query: {}", query);
        return webClient.post()
                .uri(urlBuilder -> urlBuilder.path("/.netlify/functions/index").build())
                .body(Mono.just(Map.of("query", query, "variables", variables)), Map.class)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Error from external Star Wars API " + clientResponse.statusCode())))
                .bodyToMono(FilmResponseDto.class)
                .doOnNext(response -> log.info("Response from Star Wars API: {}", response))
                .block();
    }
}
