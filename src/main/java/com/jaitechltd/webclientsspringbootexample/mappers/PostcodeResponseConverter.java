package com.jaitechltd.webclientsspringbootexample.mappers;

import com.jaitechltd.webclientsspringbootexample.domain.PostCodeIoEntity;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.PostcodeIoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        log.info("Converting PostcodeIoResponseDto to LocationResponseNewDto ...");
        return LocationResponseNewDto.builder()
                .country(locationResponseDto.getResult().getCountry())
                .latitude(locationResponseDto.getResult().getLatitude())
                .postcode(locationResponseDto.getResult().getPostcode())
                .longitude(locationResponseDto.getResult().getLongitude())
                .build();
    }

    public LocationResponseNewDto convertEntityToDto(final PostCodeIoEntity postCodeIoEntity) {
        log.info("Converting PostCodeIoEntity to LocationResponseNewDto ...");
        return LocationResponseNewDto.builder()
                .country(postCodeIoEntity.getCountry())
                .latitude(postCodeIoEntity.getLatitude())
                .postcode(postCodeIoEntity.getPostcode())
                .longitude(postCodeIoEntity.getLongitude())
                .build();
    }

    public PostCodeIoEntity convertToPostCodeIoEntity(final LocationResponseNewDto locationResponseNewDto) {
        log.info("Converting LocationResponseNewDto to PostCodeIoEntity ...");
        return PostCodeIoEntity.builder()
                .postcode(locationResponseNewDto.getPostcode())
                .country(locationResponseNewDto.getCountry())
                .latitude(locationResponseNewDto.getLatitude())
                .longitude(locationResponseNewDto.getLongitude())
                .build();
    }

    public List<LocationResponseNewDto> convertToLocationResponseNewDtoList(List<PostCodeIoEntity> postCodeIoEntities) {

        log.info("Converting List<PostCodeIoEntity> to List<LocationResponseNewDto> ...");
        return List.of(LocationResponseNewDto.builder()
                .country(postCodeIoEntities.get(0).getCountry())
                .latitude(postCodeIoEntities.get(0).getLatitude())
                .postcode(postCodeIoEntities.get(0).getPostcode())
                .longitude(postCodeIoEntities.get(0).getLongitude())
                .build());
    }
}
