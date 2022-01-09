package com.worldline.interview;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.worldline.interview.appservice.Machine;

@SpringBootTest
@AutoConfigureMockMvc
class WidgetControllerUnitTests {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
    private Machine machine;
	
	@Test
	void GivenFuelTypeIsWood_WhenQuantityIs10_ThenProductionCostShould2175() throws Exception{
		
		given(machine.produceWidgets(Mockito.anyInt())).willReturn(21.75);
		
		mvc.perform(post("/widget")
			      .contentType(MediaType.APPLICATION_JSON).content("{\n" + 
			      		"    \"engineType\":\"WOOD\",\n" + 
			      		"    \"quantity\":10\n" + 
			      		"}"))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.productionCost", is(21.75)));
	}
	@Test
	void GivenFuelTypeIsDiesel_WhenQuantityIs10_ThenProductionCostShould24() throws Exception{
		given(machine.produceWidgets(Mockito.anyInt())).willReturn((double) 24);
		
		mvc.perform(post("/widget")
			      .contentType(MediaType.APPLICATION_JSON).content("{\n" + 
			      		"    \"engineType\":\"DIESEL\",\n" + 
			      		"    \"quantity\":10\n" + 
			      		"}"))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.productionCost", is(24.0)));
	}
	@Test
	void GivenFuelTypeIsCoal_WhenQuantityIs10_ThenProductionCostShould2825() throws Exception{
		given(machine.produceWidgets(Mockito.anyInt())).willReturn(28.25);
		
		mvc.perform(post("/widget")
			      .contentType(MediaType.APPLICATION_JSON).content("{\n" + 
			      		"    \"engineType\":\"COAL\",\n" + 
			      		"    \"quantity\":10\n" + 
			      		"}"))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.productionCost", is(28.25)));
	}
	@Test
	void GivenFuelTypeIsPetrol_WhenQuantityIs10_ThenProductionCostShould18() throws Exception{
		given(machine.produceWidgets(Mockito.anyInt())).willReturn((double) 18);
		
		mvc.perform(post("/widget")
			      .contentType(MediaType.APPLICATION_JSON).content("{\n" + 
			      		"    \"engineType\":\"DIESEL\",\n" + 
			      		"    \"quantity\":10\n" + 
			      		"}"))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$.productionCost", is(18.0)));
	}
}
