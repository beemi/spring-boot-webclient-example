package com.jaitechltd.webclientsspringbootexample.controller;


import com.jaitechltd.webclientsspringbootexample.dto.postcode.LatLongResponseDto;
import com.jaitechltd.webclientsspringbootexample.service.PostcodeIoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/postcodeio")
public class PostcodeIoController {

    private final PostcodeIoService postcodeIoService;

    public PostcodeIoController(final PostcodeIoService postcodeIoService) {
        this.postcodeIoService = postcodeIoService;
    }

    @GetMapping("/getLatLong")
    @Operation(summary = "Get lat long from postcode.io", description = "Get lat long from postcode.io", tags = {"postcode-io-service"}
            , operationId = "getLatLong", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "OK", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = LatLongResponseDto.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request")})
    public ResponseEntity<?> getLatLong(@RequestParam("postcode") final String postCode) {
        return ResponseEntity.ok(postcodeIoService.getLatLong(postCode));
    }
}
