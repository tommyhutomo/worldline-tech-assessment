package com.worldline.interview.engine.impl;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("application")
@Component(StreamEngine.beanName)
public class StreamEngine extends InternalCombustionEngine{
	public static final String beanName = "StreamEngine";

	public StreamEngine() {
		super.setFuelType(Arrays.asList("WOOD","COAL"));
	}
	
	public void fill(String fuelType, int fuelLevel) {
		if (!requiredFuelType.contains(fuelType)) {
            throw new IllegalStateException("Not able to start engine.");
        }
		super.fill(fuelType, fuelLevel);
	}
}
