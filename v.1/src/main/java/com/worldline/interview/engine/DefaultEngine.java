package com.worldline.interview.engine;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.worldline.interview.utils.FuelType;

@Scope("prototype")
@Component(DefaultEngine.beanName)
public class DefaultEngine extends InternalCombustionEngine{
	public static final String beanName = "DefaultEngine";

	public DefaultEngine() {
		super.setFuelType(Arrays.asList(FuelType.DIESEL,FuelType.PETROL));
	}
}
