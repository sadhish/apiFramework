package com.example.demo;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.example.demo.StepDefinitions.BaseClass;
import com.google.common.io.Files;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Hooks extends BaseClass {
    public  String scenarioName="";
    public   String featureName="";
    @Before(order=1)
    public void testExecution(Scenario scenario) throws FileNotFoundException {
        this.featureName= Files.getNameWithoutExtension(ResourceUtils.getFile(scenario.getUri().toString()).getName());
        this.scenarioName=scenario.getName();
    }


//    public void takeScreenShotOnFailure(Scenario scenario) throws IOException {
//        if(scenario.isFailed()) {
//            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenshot());
//            scenario.log("Screenshot attached");
//        }



}
