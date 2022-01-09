package com.worldline.interview.appservice;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Machine {
	double produceWidgets(int quantity) throws JsonProcessingException; 
	void setFuelType(String fuelType);
}
