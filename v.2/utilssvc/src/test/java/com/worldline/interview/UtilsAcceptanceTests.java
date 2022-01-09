package com.worldline.interview;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UtilsAcceptanceTests extends AcceptanceTest {
	@Given("utils application is up")
	public void widget_application_is_up() {
		System.out.println("application is up");
	}
	
	@When("^the client calls /utils with fueltype (.+)$")
    public void the_client_issues(String fuelType) throws Throwable {
        executePost(fuelType);
    }
	
	@Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is wrong : " + latestResponse.getBody(), currentStatusCode.value(), is(statusCode));
    }
	
	@And("^the client receives costPerbatch is (\\d+.\\d+)$")
    public void the_client_receives_server_body(double costPerbatch) throws Throwable {
        assertThat(latestResponse.getBody(), containsString(String.valueOf(costPerbatch)));
    }
	
	@And("^the client receives batchSize is (\\d+)$")
    public void the_client_receives_server_body(int batchSize) throws Throwable {
		assertThat(latestResponse.getBody(), containsString(String.valueOf(batchSize)));
    }
	
	@And("^the client receives engineName is (.+)$")
    public void the_client_receives_server_body(String engineName) throws Throwable {
		assertThat(latestResponse.getBody(), containsString(engineName));
    }
	
	
}
