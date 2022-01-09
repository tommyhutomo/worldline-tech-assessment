package com.worldline.interview;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.worldline.interview.appservice.Machine;
import com.worldline.interview.appservice.impl.WidgetMachine;
import com.worldline.interview.engine.DefaultEngine;
import com.worldline.interview.engine.StreamEngine;
import com.worldline.interview.utils.FuelType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WidgetMachineUnitTests {

	
	@TestConfiguration
    static class ContextConfiguration {
        
		@Bean
		public ApplicationContext context() {
			ApplicationContext context = Mockito.mock(ApplicationContext.class);
			DefaultEngine dEngine=dEngine();
			Mockito.when(context.getBean("DefaultEngine")).thenReturn(dEngine);
			StreamEngine sEngine=sEngine();
			Mockito.when(context.getBean("StreamEngine")).thenReturn(sEngine);
			return context;
		}
		
		@Bean
		public DefaultEngine dEngine() {
			DefaultEngine dEngine = Mockito.mock(DefaultEngine.class);
			Mockito.when(dEngine.isRunning()).thenReturn(true);
			return dEngine;
		};
		
		@Bean
		public StreamEngine sEngine() {
			StreamEngine sEngine = Mockito.mock(StreamEngine.class);
			Mockito.when(sEngine.isRunning()).thenReturn(true);
			return sEngine;
		};
		
		@Bean
        public Machine machine() {
            return new WidgetMachine(context());
        }
    }
	
	@Autowired
	private Machine machine;
	
	@Test
	public void givenFuelisPetrol_whenQuantityEqualTo10_thenshouldreturn18(){
		machine.setFuelType(FuelType.PETROL);
		double returns=machine.produceWidgets(10);
		assertThat(returns).isEqualTo(18);
	}
	
	@Test
	public void givenFuelisDiesel_whenQuantityEqualTo10_thenshouldreturn24(){
		machine.setFuelType(FuelType.DIESEL);
		double returns=machine.produceWidgets(10);
		assertThat(returns).isEqualTo(24);
	}
	
	@Test
	public void givenFuelisWood_whenQuantityEqualTo10_thenshouldreturn24(){
		machine.setFuelType(FuelType.WOOD);
		double returns=machine.produceWidgets(10);
		assertThat(returns).isEqualTo(21.75);
	}
	
	@Test
	public void givenFuelisCoal_whenQuantityEqualTo10_thenshouldreturn24(){
		machine.setFuelType(FuelType.COAL);
		double returns=machine.produceWidgets(10);
		assertThat(returns).isEqualTo(28.25);
	}
}
