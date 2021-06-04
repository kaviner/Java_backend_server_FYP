package com.java_backend.apiserver.util;

public class RequestVerifyUtil {
    public static void printRequestBody(String apiPath,String reqBody){
        System.out.println(String.format("API [%s] Received request. Request body = %s", apiPath,reqBody));
    }
}
