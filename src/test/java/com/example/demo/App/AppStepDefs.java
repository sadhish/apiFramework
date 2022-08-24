package com.example.demo.App;

import com.example.demo.Hooks;
import com.example.demo.StepDefinitions.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
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

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;


public class AppStepDefs extends BaseClass {
    Map<String,String> testData=new LinkedHashMap<>();
    AppiumDriver driver;


    @Autowired
    private Hooks hooks;
    @Autowired
    private AppiumSetUp appiumSetUp;
    @Before
    public void setAppTestData(){

        testData=getMobileAppTestData(hooks.featureName, hooks.scenarioName);

    }
    @Given("launch the app and check login page is displayed:{string}")
    public void launchTheAppAndCheckLoginPageIsDisplayed(String platfromName) throws MalformedURLException {

        if(platfromName.equalsIgnoreCase("Android")) {
            driver = appiumSetUp.initalizeDriver(platfromName, testData.get("swagLabsAppPackage"),
                    testData.get("swagLabsAppActivity"), testData.get("UDID"), testData.get("appName"));
            driverThreadLocal.set(driver);
        }

        if(platfromName.equalsIgnoreCase("ios")) {
            driver = appiumSetUp.iosInitalizeDriver(platfromName,testData.get("IOSUDID"),testData.get("iosAppName"));
            driverThreadLocal.set(driver);
        }
    }

    @SneakyThrows
    @When("user enters login credentials")
    public void userEntersLoginCredentials() {
        WebDriverWait webDriverWait=new WebDriverWait(driverThreadLocal.get(), Duration.ofSeconds(3000));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"))).sendKeys(testData.get("username"));
        //driverThreadLocal.get().findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys(testData.get("username"));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"))).sendKeys(testData.get("password"));


        Thread.sleep(5000);
       driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();


    }
@SneakyThrows
    @Then("Homepage should be displayed in app")
    public void homepageShouldBeDisplayedInApp() {
         Thread.sleep(5000);
        driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/preceding-sibling::android.view.ViewGroup")).click();
        boolean b = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-ALL ITEMS\"]")).isDisplayed() ? true : false;
        System.out.println("hello" + b);
        Assert.assertEquals(b,true);
        driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Close\"]/android.widget.ImageView")).click();
        Thread.sleep(5000);
    }


    @Given("Launchapp:{string}")
    public void launchapp(String platformName) {
        driver=  appiumSetUp.initalizeDriver(platformName,testData.get("appPackage"),
                testData.get("appActivity"),testData.get("UDID"),testData.get("appName"));
        driverThreadLocal.set(driver);

    }
@SneakyThrows
    @And("Verify Random Elements")
    public void verifyRandomElements() {
        Thread.sleep(5000);
        driverThreadLocal.get().findElement(By.id("android:id/button1")).click();
        driverThreadLocal.get().findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
        String text="WebView";
        driverThreadLocal.get().findElement
                (new AppiumBy.ByAndroidUIAutomator
                        ("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+text+"\").instance(0))")).click();
        Thread.sleep(5000);


    }
@SneakyThrows
    @And("enter credentials:{string}")
    public void enterCredentials(String platfromName) {
        if(platfromName.equalsIgnoreCase("ios")){
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeTextField[@name=\"test-Username\"]")).sendKeys("standard_user");
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeSecureTextField[@name=\"test-Password\"]")).sendKeys("secret_sauce");
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeOther[@name=\"test-LOGIN\"]")).click();
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")).click();
        }
        if(platfromName.equalsIgnoreCase("Android")){
            WebDriverWait webDriverWait=new WebDriverWait(driverThreadLocal.get(), Duration.ofSeconds(3000));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"))).sendKeys(testData.get("username"));
            //driverThreadLocal.get().findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys(testData.get("username"));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"))).sendKeys(testData.get("password"));


            Thread.sleep(5000);
            driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();

        }
    }
    @After(order=0)
    public void tearDown() {

        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
        }
        appTestData.clear();
        driverThreadLocal.remove();
    }

@SneakyThrows
    @When("user enters login credentials:{string}")
    public void userEntersLoginCredentials(String platformName) {
        if(platformName.equalsIgnoreCase("Android")) {
            WebDriverWait webDriverWait=new WebDriverWait(driverThreadLocal.get(), Duration.ofSeconds(3000));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]"))).sendKeys(testData.get("username"));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]"))).sendKeys(testData.get("password"));
            Thread.sleep(5000);
            driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();

        }

        if(platformName.equalsIgnoreCase("ios")) {
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeTextField[@name=\"test-Username\"]")).sendKeys(testData.get("username"));
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeSecureTextField[@name=\"test-Password\"]")).sendKeys(testData.get("password"));
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeOther[@name=\"test-LOGIN\"]")).click();

        }
    }
@SneakyThrows
    @Then("Homepage should be displayed in app:{string}")
    public void homepageShouldBeDisplayedInApp(String platformName) {
        if(platformName.equalsIgnoreCase("Android")) {
            Thread.sleep(5000);
            driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/preceding-sibling::android.view.ViewGroup")).click();
            boolean b = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-ALL ITEMS\"]")).isDisplayed() ? true : false;
            System.out.println("hello" + b);
            Assert.assertEquals(b,true);
            driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Close\"]/android.widget.ImageView")).click();
            Thread.sleep(5000);
        }

         if(platformName.equalsIgnoreCase("ios")) {
            driverThreadLocal.get().findElement(By.xpath("//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")).click();
            Thread.sleep(5000);
        }
    }
}
