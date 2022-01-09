package com.worldline.interview.engine;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.worldline.interview.utils.FuelType;

@Scope("prototype")
@Component(StreamEngine.beanName)
public class StreamEngine extends InternalCombustionEngine{
	public static final String beanName = "StreamEngine";

	public StreamEngine() {
		super.setFuelType(Arrays.asList(FuelType.WOOD,FuelType.COAL));
	}
	
	public void fill(FuelType fuelType, int fuelLevel) {
		if (!requiredFuelType.contains(fuelType)) {
            throw new IllegalStateException("Not able to start engine.");
        }
		super.fill(fuelType, fuelLevel);
	}
}
