package com.jaitechltd.webclientsspringbootexample.dto.postcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostcodeIoResponseDto {
    private Result result;
    private int status;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Result {
        private String country;
        private Codes codes;
        private String ced;
        private String ccg;
        private double latitude;
        private String postcode;
        private String lsoa;
        private String msoa;
        private int eastings;
        private String nuts;
        private String parish;
        private String outcode;
        private int northings;
        private String incode;
        private String region;
        private double longitude;
        private int quality;
    }

    @Data
    public static class Codes {
        private String ccgId;
        private String lau2;
        private String ced;
        private String nuts;
        private String parish;
        private String ccg;
        private String adminWard;
        private String parliamentaryConstituency;
        private String adminCounty;
        private String adminDistrict;
        private String lsoa;
        private String msoa;
    }
}

