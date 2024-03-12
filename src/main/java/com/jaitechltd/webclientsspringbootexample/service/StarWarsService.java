package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.dto.netify.FilmResponseDto;
import com.jaitechltd.webclientsspringbootexample.webclient.StarWarsClient;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StarWarsService {

    private final StarWarsClient starWarsClient;

    public StarWarsService(StarWarsClient starWarsClient) {
        this.starWarsClient = starWarsClient;
    }

    @Timed(histogram = true)
    public FilmResponseDto getAllFilms() {
        log.info("Calling Star Wars API to get all films ...");
        return starWarsClient.getAllFilms();
    }
}
