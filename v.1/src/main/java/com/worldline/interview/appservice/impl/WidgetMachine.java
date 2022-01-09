package com.worldline.interview.appservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.worldline.interview.appservice.Machine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.utils.FuelType;

@Component
public class WidgetMachine implements Machine {
    private InternalCombustionEngine engine;
    private FuelType fuelType;
    private final int FUEL_LEVEL=100;
    
    private ApplicationContext context;
    
    @Autowired
    public WidgetMachine(ApplicationContext context) {
    	this.context=context;
    }
    
    @Override
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000") }, 
	fallbackMethod = "fallbackProduceWidgets")
    public double produceWidgets(int quantity) {
    	if (fuelType ==null) { 
    		throw new IllegalStateException("Not able produce widget as the fueltype is empty"); 
    	}
    	
    	engine = (InternalCombustionEngine)context.getBean(fuelType.getEngineName());
    	engine.fill(fuelType, FUEL_LEVEL);
        engine.start();
        double cost = 0;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private double produce(int quantity) {
        int batch = 0;
        int batchCount = 0;

        while (batch < quantity) {
            batch = batch + this.fuelType.getBatchSize();
            batchCount++;
        }

        return batchCount * this.fuelType.getCostPerbatch();
    }

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
	
	public double fallbackProduceWidgets(int quantity) {
		return 0;
	}

	
    

}
