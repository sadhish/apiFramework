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
     threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
    }
@SneakyThrows

    @When("user logged in using with proper credentials")
    public void userLoggedInUsingWithProperCredentials() {
//    driver.findElement(By.id("user-name")).sendKeys(testData.get("username"));
//    driver.findElement(By.id("password")).sendKeys( testData.get("password"));
//    driver.findElement(By.id("login-button")).click();
        Thread.sleep(4000);
        threadLocal.get().findElement(By.id("user-name")).sendKeys(testData.get("username"));
        threadLocal.get().findElement(By.id("password")).sendKeys( testData.get("password"));

       // threadLocal.get().findElement(By.id("user-name")).sendKeys(fname);
//        threadLocal.get().findElement(By.id("password")).sendKeys(lname);
        threadLocal.get().findElement(By.id("login-button")).click();
    }


    @Then("Homepage should be displayed")
    public void homepageShouldBeDisplayed() {
        System.out.println(threadLocal.get().getTitle());
        Assert.assertEquals(threadLocal.get().getTitle(),"Swag Labs");
    }
    @And("verify products is displayed")
    public void verifyProductsIsDisplayed() {

      Assert.assertEquals(threadLocal.get().
              findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText(),"PRODUCTS");
    }


@SneakyThrows
    @And("Homepage should be displayed with title")
    public void homepageShouldBeDisplayedWithTitle() {
    WebDriverWait webDriverWait=new WebDriverWait(threadLocal.get(), Duration.ofSeconds(3000));
    String expectedTitle=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='heading']"))).getText();
    Assert.assertEquals( expectedTitle,"Welcome to the-internet");
    }

    @Given("Verify Webpage title")
    public void verifyWebpageTitle() throws InterruptedException {
        launchBrowser("chrome");
        threadLocal.get().get(testData.get("url"));
        threadLocal.get().manage().window().maximize();
        threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        Assert.assertEquals("Learn Selenium with Best Practices and Examples | Selenium Easy",threadLocal.get().getTitle().toString());
    }

    @And("Verify Homepage is displayed")
    public void verifyHomepageIsDisplayed() {
        boolean expected=threadLocal.get().findElement(By.xpath("//*[@id=\"site-name\"]/a/h1")).isDisplayed();
        Assert.assertEquals(true,expected);
    }

    @And("enter practice webpage login credentials")
    public void enterPracticeWebpageLoginCredentials() {
        WebDriverWait webDriverWait=new WebDriverWait(threadLocal.get(), Duration.ofSeconds(3000));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(testData.get("username"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(testData.get("password"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit"))).click();

    }

    @Then("verify practice automation homepage is displayed")
    public void verifyPracticeAutomationHomepageIsDisplayed() {
        boolean expected=threadLocal.get().findElement(By.xpath("//*[@id=\"site-title\"]/a")).isDisplayed();
        Assert.assertEquals(true,expected);
    }



    @And("click on home link")
    public void clickOnHomeLink() {
        WebDriverWait webDriverWait=new WebDriverWait(threadLocal.get(), Duration.ofSeconds(3000));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menu-item-43\"]/a"))).click();
        //threadLocal.get().findElement(By.xpath("//*[@id=\"menu-item-43\"]/a")).click();

    }

    @And("Verify home page is launched and navigate to main page")
    public void verifyHomePageIsLaunchedAndNavigateToMainPage() {
       boolean expected= threadLocal.get().findElement(By.xpath("//*[@id=\"loop-container\"]/div/article/div[1]/h1")).isDisplayed();
       Assert.assertEquals(true,expected);
       threadLocal.get().navigate().back();
    }

    @Then("click on logout button")
    public void clickOnLogoutButton() {
        threadLocal.get().findElement(By.xpath("//*[contains(text(),'Log out')]")).click();
        Assert.assertEquals(threadLocal.get().getCurrentUrl().toString(),"https://practicetestautomation.com/practice-test-login/");
    }
    @After(order=0)
    public void tearDown() {
        if (threadLocal.get() != null) {
            threadLocal.get().quit();
            testData.clear();
            threadLocal.remove();
        }
    }



}
