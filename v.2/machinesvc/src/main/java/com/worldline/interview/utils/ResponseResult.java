package com.worldline.interview.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;

public class ResponseResult {
    private final ClientHttpResponse theResponse;
    private final String body;
    private final int statusCode;

    @SuppressWarnings("deprecation")
	public ResponseResult(final ClientHttpResponse response) throws IOException {
        this.theResponse = response;
        final InputStream bodyInputStream = response.getBody();
        final StringWriter stringWriter = new StringWriter();
        IOUtils.copy(bodyInputStream, stringWriter);
        this.body = stringWriter.toString();
        this.statusCode=response.getStatusCode().value();
    }

    public ClientHttpResponse getTheResponse() {
        return theResponse;
    }

    public String getBody() {
        return body;
    }

	public int getStatusCode() {
		return statusCode;
	}
    
}