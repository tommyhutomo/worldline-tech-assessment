package com.worldline.interview.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.worldline.interview.controller.reqresp.Request;
import com.worldline.interview.controller.reqresp.RequestFill;
import com.worldline.interview.controller.reqresp.StateResponse;
import com.worldline.interview.controller.reqresp.UtilsResponse;
import com.worldline.interview.engine.impl.InternalCombustionEngine;
import com.worldline.interview.utils.HeaderSettingRequestCallback;
import com.worldline.interview.utils.ResponseResult;
import com.worldline.interview.utils.ResponseResultErrorHandler;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/engine")
@Validated
public class EngineController {
	private final String UTILS_URL = "http://localhost:8081/v2/utils?fuelType=";
	private ApplicationContext context;
	private RestTemplate restTemplate;

	@Autowired
	public EngineController(ApplicationContext context, RestTemplate template) {
		this.context = context;
		this.restTemplate=template;
	}

	@ApiOperation(value = "Starting the Engine")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Starting the Engine"),
			@ApiResponse(code = 500, message = "Internal Error") })
	@PostMapping(path = "", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void start(@RequestBody Request request) throws Exception {
		retrieveBean(request.getFuelType()).start();
	}

	@ApiOperation(value = "Stoping the Engine")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Stoping the Engine"),
			@ApiResponse(code = 500, message = "Internal Error") })
	@PostMapping(path = "/stop", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void finish(@RequestBody Request request) throws Exception {
		retrieveBean(request.getFuelType()).stop();
	}

	@ApiOperation(value = "Fill fuel the Engine")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Fill fuel the Engine"),
			@ApiResponse(code = 500, message = "Internal Error") })
	@PostMapping(path = "/fill", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public void fill(@RequestBody RequestFill request) throws Exception {
		retrieveBean(request.getFuelType()).fill(request.getFuelType(), request.getFuelLevel());
	}

	@ApiOperation(value = "Query state of the Engine", response = StateResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Query state of the Engine"),
			@ApiResponse(code = 500, message = "Internal Error") })
	@GetMapping(path = "", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public StateResponse getCurrentState(@RequestParam String fuelType) throws Exception {
		StateResponse response = new StateResponse();
		response.setState(retrieveBean(fuelType).isRunning()?"RUNNING":"NOT RUNNING");
		return response;
	}
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, 
	fallbackMethod = "retrieveBeanFallback")
	public InternalCombustionEngine retrieveBean(String fuelType) throws JsonMappingException, JsonProcessingException {
		Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
		HeaderSettingRequestCallback requestCallback = new HeaderSettingRequestCallback(headers);
		ResponseResultErrorHandler errorHandler = new ResponseResultErrorHandler();
		
		restTemplate.setErrorHandler(errorHandler);
		ResponseResult rsp=(ResponseResult) restTemplate.execute(UTILS_URL + fuelType, HttpMethod.GET, requestCallback, response -> {
            if (errorHandler.hadError) {
                return (errorHandler.getResults());
            } else {
                return (new ResponseResult(response));
            }
        });
	    
	    
	    ObjectMapper mapper = new ObjectMapper();
	    UtilsResponse utilsResponse = mapper.readValue(rsp.getBody(), UtilsResponse.class);
		
		return (InternalCombustionEngine) context.getBean(utilsResponse.getEngineName());
	}
	public InternalCombustionEngine retrieveBeanFallback(String fuelType) {
		return (InternalCombustionEngine) context.getBean("DefaultEngine");
	}
	

}
