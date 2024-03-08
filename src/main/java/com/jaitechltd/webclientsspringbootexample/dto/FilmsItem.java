package com.jaitechltd.webclientsspringbootexample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FilmsItem{
	private String releaseDate;
	private String director;
	private SpeciesConnection speciesConnection;
	private String title;
}
