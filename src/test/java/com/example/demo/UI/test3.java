package com.example.demo.UI;

import com.example.demo.StepDefinitions.BaseClass;
import com.example.demo.StepDefinitions.MyStepdefs;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class test3 {
    static test t = new test();
    static test2 t1 = new test2();
    FutureTask<WebDriver> futureTask;

static  String result=null;
    @BeforeTest
    public void setUp() {
        futureTask = new FutureTask<WebDriver>(t);
        result=t1.getaPI().toString();
    }

    @Test
    public void testUi() throws ExecutionException, InterruptedException {
//        Thread th = new Thread(futureTask);
//      if(t1.getaPI()!=null)
//      {
//        th.start();
//        futureTask.get().get("https://www.saucedemo.com/inventory.html");
//        futureTask.get().findElement(By.id("user-name")).sendKeys(result);
//    }


    }
    @AfterTest
    public void tearDown(){

        t1.closeExecutor();
    }

    }

