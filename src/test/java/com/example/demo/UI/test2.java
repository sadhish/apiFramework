package com.example.demo.UI;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class test2  {

        static String result=null;

       public static ExecutorService executorService = Executors.newSingleThreadExecutor();
    @SneakyThrows
        public String getaPI() {
        Set<Callable<String>> callables = new HashSet<Callable<String>>();
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                RestAssured.baseURI = "https://reqres.in";
                RequestSpecification requestSpecification = RestAssured.given().contentType("application/json");
                Response response = requestSpecification.get("/api/users?page=2");
                String fname = response.jsonPath().get("data.first_name[0]");

                return fname;
            }
        });
        result = executorService.invokeAny(callables);
        System.out.println(result);
        return result.toString();
    }
     public void closeExecutor(){
     executorService.shutdown();
    }

        }








