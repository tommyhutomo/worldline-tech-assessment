package com.worldline.interview.engine.impl;

import java.util.Arrays;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("application")
@Component(DefaultEngine.beanName)
public class DefaultEngine extends InternalCombustionEngine{
	public static final String beanName = "DefaultEngine";

	public DefaultEngine() {
		super.setFuelType(Arrays.asList("DIESEL","PETROL"));
	}
}
