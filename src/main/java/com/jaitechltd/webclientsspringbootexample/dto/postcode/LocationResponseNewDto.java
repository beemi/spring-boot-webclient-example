package com.jaitechltd.webclientsspringbootexample.dto.postcode;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationResponseNewDto {

    private Result result;

    @Data
    @Builder
    public static class Result {
        private String country;
        private double latitude;
        private String postcode;
        private double longitude;
    }
}

