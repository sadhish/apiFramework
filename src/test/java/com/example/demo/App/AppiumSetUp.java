package com.example.demo.App;

import io.appium.java_client.AppiumDriver;
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
                                        String appActivity, String UDID, String appName) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        URL url = new URL(appiumUrl);

                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, UDID);
                desiredCapabilities.setCapability("appPackage", appPackage);
                desiredCapabilities.setCapability("appActivity", appActivity);
                //desiredCapabilities.setCapability("autoGrantPermissions","true");
                String appurl = System.getProperty("user.dir") + File.separator + "src" +
                        File.separator + "main" + File.separator + "resources" + File.separator + appName + ".apk";
                desiredCapabilities.setCapability(MobileCapabilityType.APP, appurl);
                return new AppiumDriver(url, desiredCapabilities);

    }

    @SneakyThrows
    public AppiumDriver iosInitalizeDriver(String platformName, String UDID, String appName) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        URL url = new URL(appiumUrl);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, UDID);
        desiredCapabilities.setCapability("simulatorStartupTimeout", 180000);
//        String appUrlIos = System.getProperty("user.dir") + File.separator + "src" +
//                File.separator + "main" + File.separator + "resources" + File.separator +"UIKitCatalog-iphonesimulator.app";
        String appUrlIos = System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "main" + File.separator + "resources" + File.separator + appName + ".app";
        desiredCapabilities.setCapability(MobileCapabilityType.APP, appUrlIos);

        return new IOSDriver(url, desiredCapabilities);
    }
}