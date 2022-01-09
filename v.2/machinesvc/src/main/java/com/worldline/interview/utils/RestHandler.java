package com.worldline.interview.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.worldline.interview.controller.reqresp.DefaultRequestEngine;
import com.worldline.interview.controller.reqresp.RequestFill;
import com.worldline.interview.controller.reqresp.StateResponse;
import com.worldline.interview.controller.reqresp.UtilsResponse;

@Component
public class RestHandler {
	
	@Value("${endpoint.util}")
	private String UTILS_URL;
	
	@Value("${endpoint.engine}")
	private String ENGINE_URL;
	
	private RestTemplate restTemplate;
	private ResponseResultErrorHandler errorHandler;

	@Autowired
	public RestHandler(RestTemplate template,
			ResponseResultErrorHandler errorHandler) {
		this.restTemplate = template;
		this.errorHandler = errorHandler;
		prepareRestTemplate();
		
	}

	public void prepareRestTemplate() {
		errorHandler = new ResponseResultErrorHandler();
		restTemplate.setErrorHandler(errorHandler);
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") })
	public void engineFill(String fuelType, int fuelLevel) throws JsonProcessingException {
		System.out.println(ENGINE_URL + "/fill");
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);
		
		RequestFill req = new RequestFill();
		req.setFuelLevel(fuelLevel);
		req.setFuelType(fuelType);

		ObjectMapper mapper = new ObjectMapper();
		requestCallback.setBody(mapper.writeValueAsString(req));
		ResponseResult rsp = (ResponseResult) restTemplate.execute(ENGINE_URL + "/fill", HttpMethod.POST,
				requestCallback, response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		if (rsp.getStatusCode() != 200) {
			throw new RuntimeException("engineFill failed");
		}
		
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") })
	public void engineStart(String fuelType) throws JsonProcessingException {
		System.out.println(ENGINE_URL);
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);
		
		DefaultRequestEngine req = new DefaultRequestEngine();
		req.setFuelType(fuelType);

		ObjectMapper mapper = new ObjectMapper();
		requestCallback.setBody(mapper.writeValueAsString(req));
		ResponseResult rsp = (ResponseResult) restTemplate.execute(ENGINE_URL, HttpMethod.POST, requestCallback,
				response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		if (rsp.getStatusCode() != 200) {
			throw new RuntimeException("engineStart failed");
		}
		
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, fallbackMethod = "isRunningFallback")
	public boolean isRunning(String fuelType) throws JsonProcessingException {
		System.out.println(ENGINE_URL+"?fuelType="+fuelType);
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);

		ResponseResult rsp = (ResponseResult) restTemplate.execute(ENGINE_URL+"?fuelType="+fuelType, HttpMethod.GET, requestCallback,
				response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		
		
		ObjectMapper mapper = new ObjectMapper();
		StateResponse response = mapper.readValue(rsp.getBody(), StateResponse.class);
		return response.getState().equals("RUNNING");
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") })
	public void engineStop(String fuelType) throws JsonProcessingException {
		System.out.println(ENGINE_URL + "/stop");
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);
		
		DefaultRequestEngine req = new DefaultRequestEngine();
		req.setFuelType(fuelType);

		ObjectMapper mapper = new ObjectMapper();
		requestCallback.setBody(mapper.writeValueAsString(req));
		ResponseResult rsp = (ResponseResult) restTemplate.execute(ENGINE_URL + "/stop", HttpMethod.POST,
				requestCallback, response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		
		if (rsp.getStatusCode() != 200) {
			throw new RuntimeException("engineStop failed");
		}

	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, fallbackMethod = "getFuelBatchSizeFallback")
	public int getFuelBatchSize(String fuelType) throws JsonMappingException, JsonProcessingException {
		System.out.println(UTILS_URL + fuelType);
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);
		
		ResponseResult rsp = (ResponseResult) restTemplate.execute(UTILS_URL + fuelType, HttpMethod.GET,
				requestCallback, response -> {
					System.out.println("getFuelBatchSize");
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		
		ObjectMapper mapper = new ObjectMapper();
		UtilsResponse response = mapper.readValue(rsp.getBody(), UtilsResponse.class);
		return response.getBatchSize();
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, fallbackMethod = "getCostPerbatchFallback")
	public double getCostPerbatch(String fuelType) throws JsonMappingException, JsonProcessingException {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);
		
		ResponseResult rsp = (ResponseResult) restTemplate.execute(UTILS_URL + fuelType, HttpMethod.GET,
				requestCallback, response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		ObjectMapper mapper = new ObjectMapper();
		UtilsResponse response = mapper.readValue(rsp.getBody(), UtilsResponse.class);
		return response.getCostPerbatch();
	}

	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, fallbackMethod = "isFuelTypeRegisteredFallback")
	public boolean isFuelTypeRegistered(String fuelType) throws JsonMappingException, JsonProcessingException {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback= new HeaderSettingRequestCallback(headers);
		
		
		ResponseResult rsp = (ResponseResult) restTemplate.execute(UTILS_URL + fuelType, HttpMethod.GET,
				requestCallback, response -> {
					if (errorHandler.hadError) {
						return (errorHandler.getResults());
					} else {
						return (new ResponseResult(response));
					}
				});
		ObjectMapper mapper = new ObjectMapper();
		UtilsResponse response = mapper.readValue(rsp.getBody(), UtilsResponse.class);
		return response == null;
	}

	public boolean isRunningFallback(String dummy) {
		return false;
	}

	public int getFuelBatchSizeFallback(String fuelType) {
		return 0;
	}

	public double getCostPerbatchFallback(String fuelType) {
		return 0;
	}

	public boolean isFuelTypeRegisteredFallback(String engineType) {
		return false;
	}

}
