package com.example.demo.StepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;
import org.testng.ITestContext;

import java.util.HashMap;

import java.util.concurrent.ExecutionException;


public class MyStepdefs extends BaseClass {

    @Value("${getIdApi}")
    public String getIdApiBaseUri;
    @Value("${userIdApi}")
    public String userIdApiBaseUri;
    public ThreadLocal<String> threadLocalBaseUri=new ThreadLocal<>();

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
        requestSpecBuilder.setBaseUri(userIdApiBaseUri).setContentType("application/json").setBody(requestPayload);
        requestSpecification=requestSpecBuilder.build();
        //requestSpecificationThreadLocal.set(requestSpecification);

        //RestAssured.baseURI= threadLocalBaseUri.get();
        //requestSpecification=RestAssured.given().contentType("application/json").body(requestPayload);
        //requestSpecificationThreadLocal.set(requestSpecification);

    }

    @When("post method is called")
    public void postMethodIsCalled() {

//       response=  requestSpecificationThreadLocal.get().post("/posts");
//       responseThreadLocal.set(response);
        response=RestAssured.given(requestSpecification).post("/posts");
    }

    @Then("response code should be {int}")
    public void responseCodeShouldBe(int arg0){
//       System.out.println( "india"+responseThreadLocal.get().getStatusCode());
//       Assert.assertEquals(responseThreadLocal.get().getStatusCode(),201);
        System.out.println( "india"+response.getStatusCode());
       Assert.assertEquals(response.getStatusCode(),201);
    }

    @Given("api")
    public void api() {
//        requestSpecBuilder.setBaseUri(getIdApiBaseUri);
//        requestSpecBuilder.setContentType("application/json");
//        requestSpecification=requestSpecBuilder.build();
//        requestSpecificationThreadLocal.set(requestSpecification);
        //threadLocalBaseUri.set(getIdApiBaseUri);
        //  RestAssured.baseURI= threadLocalBaseUri.get();
      //  requestSpecification=RestAssured.given().contentType("application/json");
       // requestSpecificationThreadLocal.set(requestSpecification);
        requestSpecBuilder.setBaseUri(getIdApiBaseUri);
        requestSpecBuilder.setContentType("application/json");
        requestSpecification=requestSpecBuilder.build();
    }

    @When("get method is called")
    public void getMethodIsCalled() throws InterruptedException {
//        response=requestSpecificationThreadLocal.get().get("/api/users?page=2");
//        responseThreadLocal.set(response);
        response=RestAssured.given(requestSpecification).get("/api/users?page=2");

    }

    @Then("check response")
    public void checkResponse() throws ExecutionException, InterruptedException {
//       Assert.assertEquals(responseThreadLocal.get().getStatusCode(),200);
//        System.out.println("heloo");
        System.out.println("heloo"+response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(),200);
//        fname=response.jsonPath().get("data.first_name[0]");
//        lname=response.jsonPath().get("data.last_name[0]");
    }
    @And("verify user id")
    public void verifyUserId() {
    }


}
