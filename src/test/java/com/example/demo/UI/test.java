package com.example.demo.UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.Callable;

public class test implements Callable<WebDriver> {

    public static WebDriver driver;
    @Override
    public WebDriver call() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/sadhishkumar.thiagarajan/Downloads/chromedriver");
        driver=new ChromeDriver();
        return driver;
    }
}
