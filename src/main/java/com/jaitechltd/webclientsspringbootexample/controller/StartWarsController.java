package com.jaitechltd.webclientsspringbootexample.controller;

import com.jaitechltd.webclientsspringbootexample.service.StarWarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Star Wars API", description = "Retrieve Star Wars data.")
@Slf4j
@RestController
@RequestMapping("/v1/api/starwars")
public class StartWarsController {

    private final StarWarsService starWarsService;

    public StartWarsController(StarWarsService starWarsService) {
        this.starWarsService = starWarsService;
    }

    @PostMapping("/allFilms")
    @Operation(summary = "Get all films", description = "Get all films",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @io.swagger.v3.oas.annotations.media.Content(
                    examples = @ExampleObject(value = "{\n" +
                            "  \"query\": \"{\\n  allFilms {\\n    films {\\n      title\\n      director\\n      releaseDate\\n      speciesConnection {\\n        species {\\n          name\\n          classification\\n          homeworld {\\n            name\\n          }\\n        }\\n      }\\n    }\\n  }\\n}\"\n" +
                            "}")
            ),
                    required = true, description = "GraphQL query to get all films"),
            operationId = "getAllFilms", responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Films found")})
    public ResponseEntity<?> getAllFilms() {
        log.info("Getting all films ...");
        return ResponseEntity.ok(starWarsService.getAllFilms());
    }

}
