package com.example.demo.UI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;

public class iostest {
@SneakyThrows
    public static void main(String[] args) {
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

       // desiredCapabilities.setCapability(MobileCapabilityType.UDID, "20084238-7ED6-4DA5-AB54-A04F9114D361");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "D00D72BC-586B-48E2-BAFA-3749BCAD357C");
        desiredCapabilities.setCapability("simulatorStartupTimeout", 180000);
//        String appUrlIos = System.getProperty("user.dir") + File.separator + "src" +
//                File.separator + "main" + File.separator + "resources" + File.separator +"UIKitCatalog-iphonesimulator.app";
    String appUrlIos = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "main" + File.separator + "resources" + File.separator +"swaglabs.app";
        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, appUrlIos);
    // desiredCapabilities.setCapability("bundleId","com.example.apple-samplecode.UICatalog");
        AppiumDriver driver=new IOSDriver(url,desiredCapabilities);
      //  driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"UIKitCatalog\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther")).click();
//driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"UIKitCatalog\"]/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeOther[2]/XCUIElementTypeOther"))


    driver.findElement(By.xpath("//XCUIElementTypeTextField[@name=\"test-Username\"]")).sendKeys("standard_user");
    driver.findElement(By.xpath("//XCUIElementTypeSecureTextField[@name=\"test-Password\"]")).sendKeys("secret_sauce");
    driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"test-LOGIN\"]")).click();
    driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"test-Menu\"]/XCUIElementTypeOther")).click();
}
}
