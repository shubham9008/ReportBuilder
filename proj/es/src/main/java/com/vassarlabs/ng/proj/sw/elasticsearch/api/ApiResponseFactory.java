package com.vassarlabs.ng.proj.sw.elasticsearch.api;


import java.rmi.server.UID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Service
public class ApiResponseFactory {

    private ApiResponseFactory () {
        //don't instantiate this class
    }

    @JsonIgnore
    public static ApiResponse createResponse(Object object) {
        ApiResponse response = new ApiResponse();
        response.setId((new UID()).toString());
        response.setResult(true, 200, object);
        return response;
    }

    @JsonIgnore
    public static ApiResponse createErrorResponse() {
        ApiResponse response = new ApiResponse();
        response.setId((new UID()).toString());
        response.setResult(false, 500, "NA");
        return response;
    }

    @JsonIgnore
    public static ApiResponse createErrorResponse(int errorCode, String errMessage) {
        ApiResponse response = new ApiResponse();
        response.setId((new UID()).toString());
        response.setResult(false, errorCode, errMessage);
        return response;
    }
}