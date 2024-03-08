package com.jaitechltd.webclientsspringbootexample.dto.netify;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FilmResponseDto {

    @JsonProperty("data")
    private DataDto data;

    @Data
    public static class DataDto {
        @JsonProperty("allFilms")
        private AllFilmsDto allFilms;
    }

    @Data
    public static class AllFilmsDto {
        private List<FilmDto> films;
    }

    @Data
    public static class FilmDto {
        private String title;
        private String director;
        @JsonProperty("releaseDate")
        private String releaseDate;
        @JsonProperty("speciesConnection")
        private SpeciesConnectionDto speciesConnection;
    }

    @Data
    public static class SpeciesConnectionDto {
        private List<SpeciesDto> species;
    }

    @Data
    public static class SpeciesDto {
        private String name;
        private String classification;
        private HomeworldDto homeworld;
    }

    @Data
    public static class HomeworldDto {
        private String name;
    }
}
