package com.jaitechltd.webclientsspringbootexample.dto.postcode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseNewDto {

    private String country;
    private double latitude;
    private String postcode;
    private double longitude;
}

