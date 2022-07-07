package com.example.demo.StepDefinitions;


import DataTransfer.DataRequest;
import com.example.demo.Hooks;
import com.google.gson.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;

public class TestDataRequest extends BaseClass {
    @Autowired
    private Hooks hooks;

    @Given("testdata")
    public void testdata() {
        DataRequest dataRequest=getTestData(hooks.featureName,hooks.scenarioName);
        JsonObject data=new JsonObject();
        data.addProperty("title",dataRequest.getTitle());
       data.addProperty("body",dataRequest.getBody());
       data.addProperty("userId",dataRequest.getUserId());
        this.requestPayload=data.toString();
    }
    @And("BaseURl")
    public void baseurl() {
    RestAssured.baseURI="https://jsonplaceholder.typicode.com";
    }
}