package com.jaitechltd.webclientsspringbootexample.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GraphQLQueryLoader {

    public GraphQLQueryLoader() {
    }

    public static String loadQueryFromFile(final String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
