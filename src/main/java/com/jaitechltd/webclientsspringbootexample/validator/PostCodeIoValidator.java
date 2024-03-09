package com.jaitechltd.webclientsspringbootexample.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PostCodeIoValidator {

    private final String POST_CODE_REGEX = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";


    /**
     * Write a method to validate the given post code in UK format.
     *
     * @param postCode post code to validate
     * @return true if the post code is valid, false otherwise
     */
    public boolean validatePostCode(final String postCode) {
        log.info("Validating post code: {}", postCode);
        return postCode.matches(POST_CODE_REGEX);
    }
}
