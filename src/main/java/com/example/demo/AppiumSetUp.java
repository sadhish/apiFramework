package com.example.demo;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;

@Component
public class AppiumSetUp {
    @Value("${spring.appiumURL}")
    public String appiumUrl;



    @SneakyThrows
    public AppiumDriver initalizeDriver(String platformName, String appPackage,
                                        String appActivity, String UDID,String appName) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        URL url = new URL(appiumUrl);
        switch (platformName) {
            case "Android":
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, UDID);
                desiredCapabilities.setCapability("appPackage", appPackage);
                desiredCapabilities.setCapability("appActivity", appActivity);
                String appurl = System.getProperty("user.dir") + File.separator + "src" +
                        File.separator + "main" + File.separator + "resources" + File.separator + appName + ".apk";
                desiredCapabilities.setCapability(MobileCapabilityType.APP, appurl);
                return new AppiumDriver(url, desiredCapabilities);


            case "IOS":
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, "090F78D0-114C-4975-8354-044067F12EF7");
                desiredCapabilities.setCapability("simulatorStartupTimeout", 180000);
                String appUrlIos = System.getProperty("user.dir") + File.separator + "src" +
                        File.separator + "main" + File.separator + "resources" + File.separator + appName + ".apk";

                desiredCapabilities.setCapability(MobileCapabilityType.APP, appUrlIos);

                return new IOSDriver(url, desiredCapabilities);
            default:
                throw new Exception("no platform found");


        }
    }
}
