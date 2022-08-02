package com.example.demo.App;

import com.example.demo.AppiumSetUp;
import com.example.demo.Hooks;
import com.example.demo.StepDefinitions.BaseClass;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;

public class AppStepDefs extends BaseClass {
    Map<String,String> testData=new LinkedHashMap<>();
    AppiumDriver driver;
    ThreadLocal<AppiumDriver> driverThreadLocal=new ThreadLocal<AppiumDriver>();

    @Autowired
    private Hooks hooks;
    @Autowired
    private AppiumSetUp appiumSetUp;
    @Before()
    public void setAppTestData(){

        testData=getMobileAppTestData(hooks.featureName, hooks.scenarioName);

    }
    @Given("launch the app and check login page is displayed:{string}")
    public void launchTheAppAndCheckLoginPageIsDisplayed(String platfromName) throws MalformedURLException {
        driver=  appiumSetUp.initalizeDriver(platfromName,testData.get("swagLabsAppPackage"),
               testData.get("swagLabsAppActivity"),testData.get("UDID"),testData.get("appName"));
        driverThreadLocal.set(driver);
        }

    @SneakyThrows
    @When("user enters login credentials")
    public void userEntersLoginCredentials() {
        driverThreadLocal.get().findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys(testData.get("username"));
       driverThreadLocal.get().findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]")).sendKeys(testData.get("password"));
       driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]")).click();
        Thread.sleep(5000);

    }
@SneakyThrows
    @Then("Homepage should be displayed in app")
    public void homepageShouldBeDisplayedInApp() {
        driverThreadLocal.get().findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/preceding-sibling::android.view.ViewGroup")).click();
        Thread.sleep(5000);
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


    @After(order=0)
    public void tearDown() {

        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
        }
    }
}
