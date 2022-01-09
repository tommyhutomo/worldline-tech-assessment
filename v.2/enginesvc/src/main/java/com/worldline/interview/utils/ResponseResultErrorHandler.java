package com.worldline.interview.utils;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ResponseResultErrorHandler implements ResponseErrorHandler {
    private ResponseResult results = null;
    public Boolean hadError = false;

    public ResponseResult getResults() {
        return results;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        hadError = response.getRawStatusCode() >= 400;
        return hadError;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        results = new ResponseResult(response);
    }
}
