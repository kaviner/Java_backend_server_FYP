package com.java_backend.apiserver.util;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestVerifyUtil {

    @Bean
    public static String printRequestBody(String apiPath,String reqBody){
        System.out.println(String.format("API [%s] Received request. Request body = %s", apiPath,reqBody));
        return null;
    }
}
