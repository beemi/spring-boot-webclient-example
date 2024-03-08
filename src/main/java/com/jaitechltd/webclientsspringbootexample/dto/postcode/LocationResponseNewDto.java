package com.jaitechltd.webclientsspringbootexample.dto.postcode;

import lombok.Data;

@Data
public class LocationResponseNewDto {
    private Result result;

    @Data
    public static class Result {
        private String country;
        private double latitude;
        private String postcode;
        private double longitude;
    }
}

