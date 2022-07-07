package com.example.demo.UI;

import com.example.demo.StepDefinitions.BaseClass;
import com.example.demo.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import java.util.LinkedHashMap;
import java.util.Map;

public class UIStepdefs extends BaseClass {
    @Autowired
    private Hooks hooks;
    Map<String,String> testData=new LinkedHashMap<>();

    @Before(order=2)
    public void setTestData(){
        testData=getUITestData(hooks.featureName, hooks.scenarioName);
    }

    @Given("navigate to loginpage")
    public void navigateToLoginpage() {
     launchBrowser("chrome");
     driver.get(testData.get("url"));
     driver.manage().window().maximize();
    }
    @SneakyThrows
    @When("user logged in using with proper credentials")
    public void userLoggedInUsingWithProperCredentials() {

    driver.findElement(By.id("user-name")).sendKeys(testData.get("username"));
    driver.findElement(By.id("password")).sendKeys( testData.get("password"));
    Thread.sleep(5000);
    driver.findElement(By.id("login-button")).click();

    }

    @Then("Homepage should be displayed")
    public void homepageShouldBeDisplayed() {
      System.out.println(driver.getTitle());
      Assert.assertEquals(driver.getTitle(),"Swag Labs");

    }
@After
    public void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}
}
