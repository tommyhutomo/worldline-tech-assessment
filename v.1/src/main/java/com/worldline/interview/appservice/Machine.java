package com.worldline.interview.appservice;

import com.worldline.interview.utils.FuelType;

public interface Machine {
	double produceWidgets(int quantity); 
	void setFuelType(FuelType fuelType);
}
