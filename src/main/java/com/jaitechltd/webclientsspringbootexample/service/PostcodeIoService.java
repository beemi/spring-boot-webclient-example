package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.webclient.PostcodeIoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostcodeIoService {

    private final PostcodeIoClient postcodeIoClient;

    public PostcodeIoService(final PostcodeIoClient postcodeIoClient) {
        this.postcodeIoClient = postcodeIoClient;
    }

    public LocationResponseNewDto getLatLong(final String postcode) {
        log.info("Calling Postcode.io external API to get lat long for postcode: {}", postcode);
        return postcodeIoClient.getLatLong(postcode);
    }
}
