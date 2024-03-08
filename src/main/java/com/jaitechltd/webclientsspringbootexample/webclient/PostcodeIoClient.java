package com.jaitechltd.webclientsspringbootexample.webclient;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PostcodeIoClient {

    public final WebClient webClient;

    public PostcodeIoClient(@Qualifier("postcodeIoWebClient") final WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Get lat long from Postcode.io
     *
     * @param postcode postcode to get lat long for
     * @return LatLongResponseDto see {@link LatLongResponseDto}
     */
    public LocationResponseDto getLatLong(final String postcode) {
        log.info("Calling Postcode.io external API to get lat long for postcode: {}", postcode);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/postcodes/" + postcode).build())
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("Error from Postcode.io API")))
                .bodyToMono(LocationResponseDto.class)
                .doOnNext(ResponseDto -> log.info("Response from Postcode.io API: {}", ResponseDto))
                .block();
    }
}
