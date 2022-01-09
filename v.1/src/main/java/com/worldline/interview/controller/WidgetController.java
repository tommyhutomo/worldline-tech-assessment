package com.worldline.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.worldline.interview.appservice.Machine;
import com.worldline.interview.controller.reqresp.Request;
import com.worldline.interview.controller.reqresp.Response;
import com.worldline.interview.utils.FuelType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/widget")
@Validated
public class WidgetController {
	
	private Machine machine;
	
	@Autowired
	public WidgetController(Machine machine) {
		this.machine=machine;
	}
	
	@ApiOperation(value = "Calculate Production cost", response = Response.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Calculate"),
			@ApiResponse(code = 500, message = "Internal Error")})
	@PostMapping(path = "",
				consumes= "application/json",
				produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Response post(@RequestBody Request request) throws Exception {
		FuelType type = FuelType.findByName(request.getEngineType());
		if (type==null) {
			throw new IllegalArgumentException("fuel type is unknown");
		}
		machine.setFuelType(type);
		Response resp = new Response();
		resp.setProductionCost(machine.produceWidgets(request.getQuantity()));
		return resp;

	}
}
