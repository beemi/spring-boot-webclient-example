package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.exception.PostCodeFormatException;
import com.jaitechltd.webclientsspringbootexample.mappers.PostcodeResponseConverter;
import com.jaitechltd.webclientsspringbootexample.validator.PostCodeIoValidator;
import com.jaitechltd.webclientsspringbootexample.webclient.PostcodeIoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostcodeIoService {

    private final PostcodeIoClient postcodeIoClient;
    private final PostCodeIoValidator postCodeIoValidator;
    private final PostcodeResponseConverter postCodeResponseConverter;

    public PostcodeIoService(final PostcodeIoClient postcodeIoClient,
                             final PostCodeIoValidator postCodeIoValidator, final PostcodeResponseConverter postCodeResponseConverter) {
        this.postCodeResponseConverter = postCodeResponseConverter;
        this.postcodeIoClient = postcodeIoClient;
        this.postCodeIoValidator = postCodeIoValidator;
    }

    /**
     * Write a method to get the latitude and longitude for the given post code.
     *
     * @param postcode post code to get lat long for
     * @return LatLongResponseDto see {@link LocationResponseNewDto}
     */
    public LocationResponseNewDto getLatLong(final String postcode) {
        log.info("Calling service to get lat long for postcode: {}", postcode);
        if (!postCodeIoValidator.validatePostCode(postcode)) {
            throw new PostCodeFormatException("Invalid post code format " + postcode);
        }
        final var locationResponseDto = postcodeIoClient.getLatLong(postcode);

        return postCodeResponseConverter.convertToLocationResponseNewDto(locationResponseDto);
    }
}
