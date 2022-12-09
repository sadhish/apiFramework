package com.example.demo.UI;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
public class test  {

public static void main(String[] args) {
    RequestSpecification requestSpecification ;
    Response response;

    RestAssured.baseURI="https://reqres.in/";
    String json="{\n" +
            "    \"name\": \"morpheus\",\n" +
            "    \"job\": \"zion sinny\"\n" +
            "}";

    requestSpecification=RestAssured.given().contentType("application/json").body(json);
    response=requestSpecification.delete("api/users/2");
//    response=requestSpecification.get("api/users?page=2");
    Assert.assertEquals(response.getStatusCode(),204,"Response code error");
System.out.println(response.getStatusCode());
System.out.println(response.getBody().prettyPrint());
//    System.out.println(response.getStatusCode());
//    System.out.println(response.getBody().prettyPrint());
//    String jsonName  =response.jsonPath().get("name");
//
//    System.out.println(jsonName);
//    Assert.assertEquals(jsonName,"sinny","Name is wrong");
//    String jsonId=response.jsonPath().get("id");
//    System.out.println(jsonId);

}
}
