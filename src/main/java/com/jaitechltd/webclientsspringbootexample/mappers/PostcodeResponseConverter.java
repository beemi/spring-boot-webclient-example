package com.jaitechltd.webclientsspringbootexample.mappers;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.PostcodeIoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostcodeResponseConverter {

    /**
     * Convert PostcodeIoResponseDto to LocationResponseNewDto
     *
     * @param locationResponseDto LocationResponseDto to convert {@link PostcodeIoResponseDto}
     * @return LocationResponseNewDto see {@link LocationResponseNewDto}
     */
    public LocationResponseNewDto convertToLocationResponseNewDto(final PostcodeIoResponseDto locationResponseDto) {
        log.info("Converting LocationResponseDto to LocationResponseNewDto");
        return LocationResponseNewDto.builder()
                .country(locationResponseDto.getResult().getCountry())
                .latitude(locationResponseDto.getResult().getLatitude())
                .postcode(locationResponseDto.getResult().getPostcode())
                .longitude(locationResponseDto.getResult().getLongitude())
                .build();
    }
}
