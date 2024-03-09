package com.jaitechltd.webclientsspringbootexample.webclient;

import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("local")
public class PostcodeIoClientTest {

    private static MockWebServer mockWebServer;
    private static PostcodeIoClient postcodeIoClient;

    @BeforeAll
    public static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString()).build();

        postcodeIoClient = new PostcodeIoClient(webClient);
    }

    @Test
    public void testGetLatLong_returnsLatLong() {

        final String postcode = "RM17 6EY";
        final String sampleResponse = """
                {
                    "result": {
                        "country": "England",
                        "latitude": 51.476936,
                        "postcode": "RM17 6EY",
                        "longitude": 0.342289
                    }
                }
                """;

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader("Accept", "application/json")
                .setResponseCode(200)
                .setBody(sampleResponse));

        LocationResponseNewDto locationResponseNewDto = postcodeIoClient.getLatLong(postcode);
        assert locationResponseNewDto != null;

        assertEquals("England", locationResponseNewDto.getResult().getCountry());
        assertEquals(51.476936, locationResponseNewDto.getResult().getLatitude());
        assertEquals("RM17 6EY", locationResponseNewDto.getResult().getPostcode());
    }

    @Test
    public void getLatLongClientError() {

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader("Accept", "application/json")
                .setResponseCode(400));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            postcodeIoClient.getLatLong("INVALID_POSTCODE");
        });

        assertTrue(exception.getMessage().contains("Error from Postcode.io API"));
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }
}
