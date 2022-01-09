package com.worldline.interview;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MachineAcceptanceTests extends AcceptanceTest {
	@Given("engine application is up")
	public void widget_application_is_up() {
		System.out.println("application is up");
	}

	@When("^the client calls POST (.+) with fueltype (.+)$")
	public void the_client_engine_post(String path, String fuelType) throws Throwable {
		if (path.contains("stop")) {
			executePostStop(fuelType);
		} else {
			executePost(fuelType);
		}
	}

	@When("^the client calls GET /engine with fueltype (.+)$")
	public void the_client_engine_get(String fuelType) throws Throwable {
		executeGet(fuelType);
	}

	@When("^client calls POST to fill with fueltype (.+) with fuelLevel is (\\d+)$")
	public void the_client_engine(String fuelType, int fuelLevel) throws Throwable {
		executePostwithFuelLevel(fuelType, fuelLevel);
	}

	@Then("^the client receives status code of (\\d+)$")
	public void the_client_receives_status_code_of(int statusCode) throws Throwable {
		final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
		assertThat("status code is wrong : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
	}

	@And("^the client receives response (.+) word$")
	public void the_client_receives_server_body(String word) throws Throwable {
		assertThat(latestResponse.getBody(), is("{\"state\":\"NOT RUNNING\"}"));
	}
}
