package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.dto.Response;
import com.jaitechltd.webclientsspringbootexample.webclient.StarWarsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class StarWarsService {

    private final StarWarsClient starWarsClient;

    public StarWarsService(StarWarsClient starWarsClient) {
        this.starWarsClient = starWarsClient;
    }

    public Response getAllFilms() {
        log.info("Calling Star Wars API to get all films ...");
        return starWarsClient.getAllFilms();
    }
}
