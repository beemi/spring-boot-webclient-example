package com.jaitechltd.webclientsspringbootexample.webclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.PostcodeIoResponseDto;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PostcodeIoClient {

    public final WebClient webClient;
    private final ObjectMapper objectMapper;

    public PostcodeIoClient(@Qualifier("postcodeIoWebClient") final WebClient webClient, final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.webClient = webClient;
    }

    /**
     * Get lat long from Postcode.io
     *
     * @param postcode postcode to get lat long for
     * @return LatLongResponseDto see {@link LocationResponseNewDto}
     */
    @Timed(histogram = true)
    public PostcodeIoResponseDto getLatLong(final String postcode) {
        log.info("Calling postcode.io external API to get lat long for postcode: {}", postcode);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/postcodes/" + postcode).build())
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Error from Postcode.io API " + clientResponse.statusCode())))
                .bodyToMono(PostcodeIoResponseDto.class)
                .doOnNext(responseDto -> {
                    try {
                        final String jsonResponse = objectMapper.writeValueAsString(responseDto);
                        log.info("Response from Postcode.io external API: {}", jsonResponse);
                    } catch (JsonProcessingException e) {
                        log.error("Error serializing responseDto", e);
                    }
                })
                .block();
    }
}
