package com.example.demo.StepDefinitions;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(


        features = "src/test/resources/features",
        glue = { "com.example.demo","classpath/StepDefinitions","classpath/UIStepdefs"
               },
        plugin = {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:test-output/cucumber-report/cucumber.json",
                "html:test-output/cucumber-report/cucumber.html","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },monochrome = true,tags =("@SANITY")
)

@Test
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}





