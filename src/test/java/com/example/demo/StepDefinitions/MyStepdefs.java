package com.example.demo.StepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.HashMap;

public class MyStepdefs extends BaseClass {


    @Given("valid userid:{string},{string},{string},{string}")
    public void validUserid(String url, String title, String body, String userid) {
        HashMap<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("title", title);
        reqMap.put("body", body);
        reqMap.put("userId", userid);
        RestAssured.baseURI=url;

       requestSpecification=RestAssured.given().contentType("application/json").body(reqMap);
    }

    @When("post method is called:{string}")
    public void postMethodIsCalled(String endPoint) {
        response=requestSpecification.post(endPoint);
    }

    @And("verify Userid:{string}")
    public void verifyUserid(String userId) {
        String expectedUserId=response.jsonPath().get("userId").toString();
        Assert.assertEquals(userId,expectedUserId);
    }

    @Then("response code should be {int}:{string}")
    public void responseCodeShouldBe(int arg0, String statuscode) {
        int expectedStatusCode=response.getStatusCode();
        Assert.assertEquals(statuscode,Integer.toString(expectedStatusCode));
    }


    @Given("I want to write a step with :{string}")
    public void iWantToWriteAStepWith(String string) {
    System.out.println("hi");
    }

    @When("I check for the {string} in step")
    public void iCheckForTheInStep(String arg0) {
        System.out.println("hi");
    }

    @Then("I verify the {string}in step")
    public void iVerifyTheInStep(String arg0) {

        System.out.println("hi");
    }


    @Given("valid userid")
    public void validUserid() {

        requestSpecification=RestAssured.given().contentType("application/json").body(requestPayload);
    }

    @When("post method is called")
    public void postMethodIsCalled() {
       response=requestSpecification.post("/posts");
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int arg0) {
       Assert.assertEquals(response.getStatusCode(),201);
    }
}
