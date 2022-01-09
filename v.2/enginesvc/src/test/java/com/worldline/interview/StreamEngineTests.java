package com.worldline.interview;

import org.junit.Test;

import com.worldline.interview.engine.impl.StreamEngine;

public class StreamEngineTests {

	
//	It supports only two types of fuel – wood and coal (it cannot be filled with any other
//	type of fuel)
	
	@Test
	public void givenwood_whencallfuel_thenshouldok() {
		StreamEngine engine = new StreamEngine();
		engine.fill("WOOD", 100);
	}
	@Test(expected = IllegalStateException.class)
	public void givenwood_whencallfuel_thenshouldfail() {
		StreamEngine engine = new StreamEngine();
		engine.fill("PETROL", 100);
	}
	
//	In order to start, a steam engine must meet the following conditions:
//		o The fuel level must be greater than zero
//		o The engine must have been filled with the required fuel type
	
	@Test
	public void givenenginewithvalidfuel_whenstart_thenshouldok() {
		StreamEngine engine = new StreamEngine();
		engine.fill("WOOD",100);
		engine.start();
	}
	
	@Test(expected = IllegalStateException.class)
	public void givennofuel_whenstart_thenshouldfail() {
		StreamEngine engine = new StreamEngine();
		engine.start();
	}
}
