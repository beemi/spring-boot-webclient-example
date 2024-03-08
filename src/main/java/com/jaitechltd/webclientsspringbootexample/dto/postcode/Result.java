package com.jaitechltd.webclientsspringbootexample.dto.postcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {
    private String country;
    private Codes codes;
    private Object ced;
    private String ccg;
    private double latitude;
    private String postcode;
    private String europeanElectoralRegion;
    private String parliamentaryConstituency;
    private String adminWard;
    private int eastings;
    private Object adminCounty;
    private String lsoa;
    private String msoa;
    private String adminDistrict;
    private int quality;
    private String primaryCareTrust;
    private String nuts;
    private String parish;
    private String outcode;
    private int northings;
    private String nhsHa;
    private String incode;
    private String region;
    private double longitude;
}
