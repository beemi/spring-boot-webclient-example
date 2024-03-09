package com.jaitechltd.webclientsspringbootexample.service;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.validator.PostCodeIoValidator;
import com.jaitechltd.webclientsspringbootexample.webclient.PostcodeIoClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private PostcodeIoClient postcodeIoClient;

    @Mock
    private PostCodeIoValidator postCodeIoValidator;

    @InjectMocks
    private PostcodeIoService postcodeIoService;

    @BeforeEach
    void setUp() {
        postcodeIoService = new PostcodeIoService(postcodeIoClient, postCodeIoValidator);
    }

    @Test
    void getLatLong_ValidPostcode_ReturnsLocation() {
        // Arrange
        final String validPostcode = "RM17 6EY";
        final var expectedResponse = LocationResponseNewDto.builder()
                .result(LocationResponseNewDto.Result.builder()
                        .country("England")
                        .latitude(51.501009)
                        .postcode("RM17 6EY")
                        .longitude(-0.141588)
                        .build());

        when(postCodeIoValidator.validatePostCode(validPostcode)).thenReturn(true);
        when(postcodeIoClient.getLatLong(validPostcode)).thenReturn(expectedResponse.build());

        // Act
        LocationResponseNewDto actualResponse = postcodeIoService.getLatLong(validPostcode);

        // Assert
        Assertions.assertEquals(expectedResponse.build(), actualResponse);
        Assertions.assertEquals(expectedResponse.build().getResult().getCountry(), actualResponse.getResult().getCountry());
    }
}
