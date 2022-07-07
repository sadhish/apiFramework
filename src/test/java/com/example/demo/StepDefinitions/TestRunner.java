package com.example.demo.StepDefinitions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.After;
import org.testng.annotations.Test;


@CucumberOptions(


        features = "src/test/resources/features",
        glue = { "com.example.demo","classpath/StepDefinitions","classpath/UIStepdefs"
               },
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/cucumber-report/cucumber.json",
                "html:target/cucumber-report/cucumber.html"

},dryRun = false,monochrome = true,tags =("@login")
)

@Test
public class TestRunner extends AbstractTestNGCucumberTests {
}





