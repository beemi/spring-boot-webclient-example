package com.jaitechltd.webclientsspringbootexample.dto;

import lombok.*;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class SpeciesItem{
	private Homeworld homeworld;
	private String name;
	private String classification;
}
