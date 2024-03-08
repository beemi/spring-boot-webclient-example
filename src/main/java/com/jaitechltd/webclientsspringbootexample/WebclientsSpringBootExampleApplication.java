package com.jaitechltd.webclientsspringbootexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WebclientsSpringBootExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebclientsSpringBootExampleApplication.class, args);
        log.info("Webclients Spring Boot Example started ...");
    }

}
