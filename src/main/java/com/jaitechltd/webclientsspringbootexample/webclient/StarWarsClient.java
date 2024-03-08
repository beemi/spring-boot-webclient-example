package com.jaitechltd.webclientsspringbootexample.webclient;

import com.jaitechltd.webclientsspringbootexample.dto.netify.FilmResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class StarWarsClient {

    protected WebClient webClient;

    public StarWarsClient(@Qualifier("starWarsWebClient") final WebClient webClient) {
        this.webClient = webClient;
    }

    // Define the GraphQL query as a separate method
    private String buildGraphQLQuery() {
        return "{\n" +
                "  \"query\": \"{\\n  allFilms {\\n    films {\\n      title\\n      director\\n      releaseDate\\n      speciesConnection {\\n        species {\\n          name\\n          classification\\n          homeworld {\\n            name\\n          }\\n        }\\n      }\\n    }\\n  }\\n}\"\n" +
                "}";
    }

    public FilmResponseDto getAllFilms() {
        final String query = buildGraphQLQuery();
        log.info("Calling Star Wars external API to get all films wth query: {}", query);
        return webClient.post()
                .uri(urlBuilder -> urlBuilder.path("/.netlify/functions/index").build())
                .bodyValue(query)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Error from Star Wars API")))
                .bodyToMono(FilmResponseDto.class)
                .doOnNext(response -> log.info("Response from Star Wars API: {}", response))
                .block();
    }
}
