package com.worldline.interview;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = UtilsApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class AcceptanceTest {
	static ResponseResult latestResponse = null;
	
    @Autowired
    protected RestTemplate restTemplate;
    
    void executePost(String fuelType) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
          .execute("http://localhost:8081/v2/utils?fuelType="+fuelType, HttpMethod.GET, requestCallback, response -> {
              if (errorHandler.hadError) {
                  return (errorHandler.getResults());
              } else {
                  return (new ResponseResult(response));
              }
          });
    }
    
    private class ResponseResultErrorHandler implements ResponseErrorHandler {
        private ResponseResult results = null;
        private Boolean hadError = false;

        private ResponseResult getResults() {
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
}
