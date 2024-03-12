package com.jaitechltd.webclientsspringbootexample.exception;


import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerErrorAdvice {

    @ExceptionHandler(PostCodeFormatException.class)
    public ResponseEntity<ErrorResponse> handlePostCodeFormatException(final PostCodeFormatException ex) {
        log.error("Invalid post code: {} is not a valid UK post code", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, List.of(ex.getMessage())));
    }

    @ExceptionHandler({
            BadRequestException.class
    })
    public ResponseEntity<ErrorResponse> handleBadRequestException(final BadRequestException ex) {
        log.error("Invalid request: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST, List.of(ex.getMessage())));
    }

    @ExceptionHandler({
            Exception.class,
            RuntimeException.class,
            JSQLParserException.class
    })
    public ResponseEntity<ErrorResponse> handleException(final Exception ex) {
        log.error("An error occurred while processing the request: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                List.of("An error occurred while processing the request")));
    }

    @ExceptionHandler({
            PosstCodeNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handlePosstCodeNotFoundException(final PosstCodeNotFoundException ex) {
        log.error("Post code not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, List.of(ex.getMessage())));
    }

    public record ErrorResponse(HttpStatus status, List<String> message) {
    }
}
