package com.worldline.interview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.interview.controller.reqresp.Response;
import com.worldline.interview.utils.FuelType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/utils")
@Validated
public class UtilsController {

	
	@ApiOperation(value = "Get Fuel details", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully response"),
			@ApiResponse(code = 345, message = "Fuel unknown"),
			@ApiResponse(code = 500, message = "Internal Error")})
	@GetMapping(path = "",
				consumes= "application/json",
				produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Response post(@RequestParam String fuelType) throws Exception {
		FuelType type = FuelType.findByName(fuelType);
		if (type == null) {
			throw new IllegalArgumentException ("Fuel type unknown");
		}
		Response rsp = new Response();
		rsp.setBatchSize(type.getBatchSize());
		rsp.setCostPerbatch(type.getCostPerbatch());
		rsp.setEngineName(type.getEngineName());
		return rsp;

	}
}
