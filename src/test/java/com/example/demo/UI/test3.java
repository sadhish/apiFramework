package com.example.demo.UI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class test3 {
//    static test t = new test();
//    static test2 t1 = new test2();
//    FutureTask<WebDriver> futureTask;
//
//static  String result=null;
//    @BeforeTest
//    public void setUp() {
//        futureTask = new FutureTask<WebDriver>(t);
//        result=t1.getaPI().toString();
//    }
//
//    @Test
//    public void testUi() throws ExecutionException, InterruptedException {
////        Thread th = new Thread(futureTask);
////      if(t1.getaPI()!=null)
////      {
////        th.start();
////        futureTask.get().get("https://www.saucedemo.com/inventory.html");
////        futureTask.get().findElement(By.id("user-name")).sendKeys(result);
////    }
//
//
//    }
//    @AfterTest
//    public void tearDown(){
//
//        t1.closeExecutor();
//    }
public static void main(String[] args) {
    RequestSpecification requestSpecification=
            RestAssured.given().baseUri("https://gorest.co.in");
    Response response=requestSpecification.get("/public/v2/users/3");
    System.out.println(response.getStatusCode());
}
    }

