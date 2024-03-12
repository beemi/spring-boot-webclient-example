package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.dto.postcode.PostcodeIoResponseDto;
import com.jaitechltd.webclientsspringbootexample.exception.PostCodeFormatException;
import com.jaitechltd.webclientsspringbootexample.mappers.PostcodeResponseConverter;
import com.jaitechltd.webclientsspringbootexample.validator.PostCodeIoValidator;
import com.jaitechltd.webclientsspringbootexample.webclient.PostcodeIoClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("local")
public class PostCodeServiceTest {

    @Mock
    PostcodeIoClient postcodeIoClient;

    @Mock
    PostCodeIoValidator postCodeIoValidator;

    @Mock
    PostcodeResponseConverter postCodeResponseConverter;

    @InjectMocks
    PostcodeIoService postcodeIoService;

    @BeforeEach
    void setUp() {
        postcodeIoService = new PostcodeIoService(postcodeIoClient, postCodeIoValidator, postCodeResponseConverter);
    }

    @Test
    @DisplayName("Given a valid postcode, when getLatLong is called, then it should return the location")
    void getLatLong_ValidPostcode_ReturnsLocation() {
        // Arrange
        final String validPostcode = "RM17 6EY";
        final var postcodeIoResponse = PostcodeIoResponseDto.builder()
                .result(PostcodeIoResponseDto.Result.builder()
                        .country("England")
                        .latitude(51.501009)
                        .postcode(validPostcode)
                        .longitude(-0.141588)
                        .build());
        final var expectedResponse = LocationResponseNewDto.builder()
                .country("England")
                .latitude(51.501009)
                .postcode(validPostcode)
                .longitude(-0.141588)
                .build();

        when(postCodeIoValidator.validatePostCode(validPostcode)).thenReturn(true);
        when(postcodeIoClient.getLatLong(validPostcode)).thenReturn(postcodeIoResponse.build());
        when(postCodeResponseConverter.convertToLocationResponseNewDto(postcodeIoResponse.build())).thenReturn(expectedResponse);

        // Act
        LocationResponseNewDto actualResponse = postcodeIoService.getLatLong(validPostcode);

        // Assert
        Assertions.assertEquals(expectedResponse, actualResponse);
        Assertions.assertEquals(expectedResponse.getCountry(), actualResponse.getCountry());
    }

    @ParameterizedTest
    @ValueSource(strings = {"RM176EY", "INVALID", "RM17 6EY "})
    @DisplayName("Given an invalid postcode {0}, when getLatLong is called, then it should throw PostCodeFormatException")
    void getLatLong_InvalidPostcode_ThrowsPostCodeFormatException(final String invalidPostcode) throws PostCodeFormatException {
        // Arrange
        when(postCodeIoValidator.validatePostCode(invalidPostcode)).thenReturn(false);

        // Act and Assert
        Assertions.assertThrows(PostCodeFormatException.class, () -> postcodeIoService.getLatLong(invalidPostcode));
    }

}
