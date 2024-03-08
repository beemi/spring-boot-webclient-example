package com.jaitechltd.webclientsspringbootexample.dto;

import java.util.List;

import lombok.*;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class SpeciesConnection{
	private List<SpeciesItem> species;
}
