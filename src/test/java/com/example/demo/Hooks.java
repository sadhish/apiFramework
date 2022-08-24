package com.example.demo;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.example.demo.StepDefinitions.BaseClass;
import com.google.common.io.Files;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Hooks extends BaseClass {
    public  String scenarioName="";
    public   String featureName="";

    @Before(order=0)
    public void testExecution(Scenario scenario) throws FileNotFoundException {
        this.featureName= Files.getNameWithoutExtension(ResourceUtils.getFile(scenario.getUri().toString()).getName());
        this.scenarioName=scenario.getName();

    }


@Before(order=0)
public void launchApp(){

}

    @Attachment(value="page screenshot",type = "image/png")
    public byte[] saveScreenshot(byte[] screenshot){
        return screenshot;
    }


    @After(order=1)
    public void takeScreenshot(Scenario scenario) throws IOException {
//        if(driver!=null) {
//             if(scenario.isFailed()) {
//                    Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).
//                    getScreenshotAs(OutputType.BYTES)));
//                 }
//        }
        if(threadLocal.get()!=null) {
            if(scenario.isFailed()) {
                Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) threadLocal.get()).
                        getScreenshotAs(OutputType.BYTES)));
            }
        }
        if(driverThreadLocal.get()!=null){
            if(scenario.isFailed()){
                Allure.addAttachment("Mobile screenshot", new ByteArrayInputStream(((TakesScreenshot) threadLocal.get()).
                        getScreenshotAs(OutputType.BYTES)));
            }
        }
        }

//    @After(order=2)
//    public void takeScreenShotOnFailure(Scenario scenario) throws IOException {
//
//            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenshot()); //
//            scenario.log("Screenshot attached");
//
//    }



}
