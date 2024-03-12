package com.jaitechltd.webclientsspringbootexample.config.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties("externalservice")
public class ExternalApiProperties {

    private PostCodeIoApi postCodeIoApi;
    private StarWarsApi starWarsApi;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class PostCodeIoApi {
        private String baseUrl;
        private String token;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class StarWarsApi {
        private String baseUrl;
    }
}
