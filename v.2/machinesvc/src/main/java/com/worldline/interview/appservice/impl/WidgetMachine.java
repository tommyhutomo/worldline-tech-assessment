package com.worldline.interview.appservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.worldline.interview.appservice.Machine;
import com.worldline.interview.utils.RestHandler;

@Component
public class WidgetMachine implements Machine {
	private String fuelType;
	private final int FUEL_LEVEL = 100;

	private RestHandler handler;

	@Autowired
	public WidgetMachine(RestHandler handler) {
		this.handler = handler;
	}

	public double produceWidgets(int quantity) throws JsonProcessingException {

		double cost = 0;

		handler.engineFill(fuelType, FUEL_LEVEL);
		handler.engineStart(fuelType);

		if (handler.isRunning(fuelType)) {
			cost = produce(quantity);
		}
		handler.engineStop(fuelType);

		return cost;
	}

	private double produce(int quantity) throws JsonMappingException, JsonProcessingException {
		int batch = 0;
		int batchCount = 0;

		while (batch < quantity) {
			batch = batch + handler.getFuelBatchSize(fuelType);
			batchCount++;
		}

		return batchCount * handler.getCostPerbatch(fuelType);
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public double fallbackProduceWidgets(int quantity) {
		return 0;
	}

}
