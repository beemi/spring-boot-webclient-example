package com.jaitechltd.webclientsspringbootexample.dto.postcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Codes {
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
