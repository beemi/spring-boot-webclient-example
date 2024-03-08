package com.jaitechltd.webclientsspringbootexample.dto;

import lombok.*;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Response {
    private Data data;
}
