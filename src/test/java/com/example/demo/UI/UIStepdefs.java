package com.example.demo.UI;

import com.example.demo.StepDefinitions.BaseClass;
import com.example.demo.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class UIStepdefs extends BaseClass {
    @Autowired
    private Hooks hooks;
    Map<String,String> testData=new LinkedHashMap<>();

    @Before
    public void setTestData(){

        testData=getUITestData(hooks.featureName, hooks.scenarioName); //getsTestData
    }

    @Given("navigate to loginpage")
    public void navigateToLoginpage() {
     launchBrowser("chrome");
     threadLocal.get().get(testData.get("url"));
     threadLocal.get().manage().window().maximize();
    }


    @When("user logged in using with proper credentials")
    public void userLoggedInUsingWithProperCredentials() {
//    driver.findElement(By.id("user-name")).sendKeys(testData.get("username"));
//    driver.findElement(By.id("password")).sendKeys( testData.get("password"));
//    driver.findElement(By.id("login-button")).click();

        threadLocal.get().findElement(By.id("user-name")).sendKeys(testData.get("username"));
        threadLocal.get().findElement(By.id("password")).sendKeys( testData.get("password"));
        threadLocal.get().findElement(By.id("login-button")).click();
    }


    @Then("Homepage should be displayed")
    public void homepageShouldBeDisplayed() {
      System.out.println(threadLocal.get().getTitle());
      //Assert.assertEquals(driver.getTitle(),"Swag Labs");
        Assert.assertEquals(threadLocal.get().getTitle(),"Swag Labs");
    }




    @And("verify products is displayed")
    public void verifyProductsIsDisplayed() {

      Assert.assertEquals(threadLocal.get().findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText(),"PRODUCTS");
    }



//    @When("user enters login credentials in applitools login page")
//    public void userEntersLoginCredentialsInApplitoolsLoginPage() {
//        threadLocal.get().findElement(By.id("username")).sendKeys(testData.get("username"));
//        threadLocal.get().findElement(By.id("password")).sendKeys( testData.get("password"));
//        threadLocal.get().findElement(By.id("log-in")).click();
//    }

@SneakyThrows
    @And("Homepage should be displayed with title")
    public void homepageShouldBeDisplayedWithTitle() {
    WebDriverWait webDriverWait=new WebDriverWait(threadLocal.get(), Duration.ofSeconds(3000));
    String expectedTitle=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='heading']"))).getText();
    Assert.assertEquals( expectedTitle,"Welcome to the-internet");

    }

    @After(order=0)
    public void tearDown() {
//    if (driver != null) {
//        driver.quit();
//    }
        if (threadLocal.get() != null) {
            threadLocal.get().quit();
        }
    }
}
