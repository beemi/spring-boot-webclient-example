package com.jaitechltd.webclientsspringbootexample.controller;


import com.jaitechltd.webclientsspringbootexample.dto.postcode.LocationResponseNewDto;
import com.jaitechltd.webclientsspringbootexample.exception.ControllerErrorAdvice;
import com.jaitechltd.webclientsspringbootexample.service.PostcodeIoService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/api/postcodeio")
public class PostcodeIoController {

    private final PostcodeIoService postcodeIoService;

    public PostcodeIoController(final PostcodeIoService postcodeIoService) {
        this.postcodeIoService = postcodeIoService;
    }

    @GetMapping("/getLatLong")
    @Operation(summary = "Get lat long from postcode.io", description = "Get lat long from postcode.io", tags = {"Postcode API"},
            operationId = "getLatLong", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LocationResponseNewDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ControllerErrorAdvice.ErrorResponse.class)))
    })
    @Timed(histogram = true)
    public ResponseEntity<?> getLatLong(@RequestParam("postcode") final String postCode) {
        log.info("Received request to get lat long for postcode: {}", postCode);
        LocationResponseNewDto responseDto = postcodeIoService.getLatLong(postCode);
        log.info("Sending response to client for postcode: {}: {}", postCode, responseDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/details/{id}")
    @Operation(summary = "Retrieve stored Postcode details.", description = "Retrieve stored Postcode details.", tags = {"Postcode API"},
            operationId = "getPostCodeDetails", responses = {
            @ApiResponse(responseCode = "200", description = "Stored Postcode details.", content = @Content(schema = @Schema(implementation = LocationResponseNewDto.class))),
            @ApiResponse(responseCode = "404", description = "Postcode details not found.", content = @Content(schema = @Schema(implementation = ControllerErrorAdvice.ErrorResponse.class)))
    })
    @Timed(histogram = true)
    public ResponseEntity<LocationResponseNewDto> getPostCodeDetails(@PathVariable("id") final Long id) {
        log.info("Received request to get post code details for id: {}", id);
        Optional<LocationResponseNewDto> postCodeDetails = postcodeIoService.getById(id);
        return postCodeDetails.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/postCodeDetails")
    @Operation(summary = "Get post code details from postcode.io", description = "Get post code details from postcode.io", tags = {"Postcode API"},
            operationId = "postCodeDetails", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LocationResponseNewDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ControllerErrorAdvice.ErrorResponse.class)))
    })
    @Timed(histogram = true)
    public ResponseEntity<Optional<List<LocationResponseNewDto>>> postCodeDetails(@RequestParam("postcode") final String postCode) {
        log.info("Received request to get post code details for postcode: {}", postCode);
        Optional<List<LocationResponseNewDto>> postCodeDetails = postcodeIoService.getPostCodeDetails(postCode);
        return ResponseEntity.ok(postCodeDetails);
    }
}
