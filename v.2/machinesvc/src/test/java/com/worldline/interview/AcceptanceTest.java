package com.worldline.interview;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.worldline.interview.utils.HeaderSettingRequestCallback;
import com.worldline.interview.utils.ResponseResult;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = WidgetApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations="classpath:test.properties")
public class AcceptanceTest {
	static ResponseResult latestResponse = null;

	private final WireMockServer wireMockServer = new WireMockServer(options().port(9999));
    @Autowired
    protected RestTemplate restTemplate;
    
    void executePost(String fuelType, int quantity) throws IOException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        final HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
        final ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
        
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo("/v2/utils?fuelType=PETROL")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200).withBody("{\r\n" + 
                		"    \"costPerbatch\": 9.0,\r\n" + 
                		"    \"batchSize\": 8,\r\n" + 
                		"    \"engineName\": \"DefaultEngine\"\r\n" + 
                		"}")));
        
        stubFor(get(urlEqualTo("/v2/utils?fuelType=DIESEL")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200).withBody("{\r\n" + 
                		"    \"costPerbatch\": 12.0,\r\n" + 
                		"    \"batchSize\": 8,\r\n" + 
                		"    \"engineName\": \"DefaultEngine\"\r\n" + 
                		"}")));
        
        stubFor(get(urlEqualTo("/v2/utils?fuelType=COAL")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200).withBody("{\r\n" + 
                		"    \"costPerbatch\": 5.65,\r\n" + 
                		"    \"batchSize\": 2,\r\n" + 
                		"    \"engineName\": \"DefaultEngine\"\r\n" + 
                		"}")));
        
        stubFor(get(urlEqualTo("/v2/utils?fuelType=WOOD")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200).withBody("{\r\n" + 
                		"    \"costPerbatch\": 4.35,\r\n" + 
                		"    \"batchSize\": 2,\r\n" + 
                		"    \"engineName\": \"DefaultEngine\"\r\n" + 
                		"}")));

        stubFor(get(urlEqualTo("/v2/engine?fuelType="+fuelType)).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200).withBody("{\r\n" + 
                		"    \"state\": \"RUNNING\"\r\n" + 
                		"}")));
        
        stubFor(post(urlEqualTo("/v2/engine")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200)));
        
        stubFor(post(urlEqualTo("/v2/engine/fill")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200)));
        
        stubFor(post(urlEqualTo("/v2/engine/stop")).inScenario("test")
                .withHeader("content-type", equalTo("application/json"))
                .willReturn(aResponse().withStatus(200)));
        
        
        
        requestCallback.setBody("{\r\n" + 
        		"    \"engineType\":\""+fuelType+"\",\r\n" + 
        		"    \"quantity\":"+quantity+"\r\n" + 
        		"}");
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        restTemplate.setErrorHandler(errorHandler);
        latestResponse = restTemplate
          .execute("http://localhost:8083/v2/widget", HttpMethod.POST, requestCallback, response -> {
              if (errorHandler.hadError) {
                  return (errorHandler.getResults());
              } else {
                  return (new ResponseResult(response));
              }
          });
        wireMockServer.stop();
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
