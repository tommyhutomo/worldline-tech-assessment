package com.worldline.interview;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WidgetAcceptanceTests extends AcceptanceTest {
	@Given("widget application is up")
	public void widget_application_is_up() {
		System.out.println("application is up");
	}
	
	@When("^the client calls /widget with fueltype (.+) and quantity (\\d+)$")
    public void the_client_issues(String fuelType, int quantiy) throws Throwable {
        executePost(fuelType,quantiy);
    }
	
	@Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is wrong : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }
	
	@And("^the client receives productionCost (.+)$")
    public void the_client_receives_server_body(String productionCost) throws Throwable {
        assertThat(latestResponse.getBody(), is("{\"productionCost\":"+productionCost+"}"));
    }
}
