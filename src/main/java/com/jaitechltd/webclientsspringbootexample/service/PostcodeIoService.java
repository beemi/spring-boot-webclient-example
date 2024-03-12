package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.domain.PostCodeIoEntity;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.exception.PosstCodeNotFoundException;
import com.jaitechltd.webclientsspringbootexample.exception.PostCodeFormatException;
import com.jaitechltd.webclientsspringbootexample.mappers.PostcodeResponseConverter;
import com.jaitechltd.webclientsspringbootexample.repository.PostCodeIoRepository;
import com.jaitechltd.webclientsspringbootexample.validator.PostCodeIoValidator;
import com.jaitechltd.webclientsspringbootexample.webclient.PostcodeIoClient;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostcodeIoService {

    private final PostcodeIoClient postcodeIoClient;
    private final PostCodeIoValidator postCodeIoValidator;
    private final PostcodeResponseConverter postCodeResponseConverter;
    private final PostCodeIoRepository postCodeIoRepository;

    public PostcodeIoService(final PostcodeIoClient postcodeIoClient,
                             final PostCodeIoValidator postCodeIoValidator, final PostcodeResponseConverter postCodeResponseConverter, PostCodeIoRepository postCodeIoRepository) {
        this.postCodeResponseConverter = postCodeResponseConverter;
        this.postcodeIoClient = postcodeIoClient;
        this.postCodeIoValidator = postCodeIoValidator;
        this.postCodeIoRepository = postCodeIoRepository;
    }

    /**
     * Write a method to get the latitude and longitude for the given post code.
     *
     * @param postcode post code to get lat long for
     * @return LatLongResponseDto see {@link LocationResponseNewDto}
     */
    @Timed(histogram = true)
    public LocationResponseNewDto getLatLong(final String postcode) {
        log.info("Calling service to get lat long for postcode: {}", postcode);
        if (!postCodeIoValidator.validatePostCode(postcode)) {
            throw new PostCodeFormatException("Invalid post code format " + postcode);
        }
        final var locationResponseDto = postcodeIoClient.getLatLong(postcode);
        LocationResponseNewDto response = postCodeResponseConverter.convertToLocationResponseNewDto(locationResponseDto);

        postCodeIoRepository.save(postCodeResponseConverter.convertToPostCodeIoEntity(response));

        log.info("Service response for postcode {}: {}", postcode, response); // Consider logging summary or key info
        return response;
    }

    public Optional<List<LocationResponseNewDto>> getPostCodeDetails(final String postCode) {

        Optional<List<PostCodeIoEntity>> byPostcode = postCodeIoRepository.findByPostcode(postCode);

        return byPostcode.map(postCodeIoEntities -> postCodeResponseConverter.convertToLocationResponseNewDtoList(postCodeIoEntities));
    }

    /**
     * Write a method to get the post code details for the given id.
     *
     * @param id id to get post code details for
     * @return LocationResponseNewDto see {@link LocationResponseNewDto}
     */
    public Optional<LocationResponseNewDto> getById(final Long id) {
        Optional<?> postCodeIoEntity = postCodeIoRepository.findById(id);
        if (postCodeIoEntity.isEmpty()) {
            throw new PosstCodeNotFoundException("Post code not found for id: " + id);
        }
        return postCodeIoEntity.map(m -> postCodeResponseConverter.convertEntityToDto((PostCodeIoEntity) m));
    }
}
