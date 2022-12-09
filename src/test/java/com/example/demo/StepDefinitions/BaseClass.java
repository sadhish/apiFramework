package com.example.demo.StepDefinitions;

import DataTransfer.DataRequest;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.appium.java_client.AppiumDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

public class BaseClass {
    public  RequestSpecification requestSpecification;
    public RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
    public  Response response;
    public static  ThreadLocal<WebDriver> threadLocal=new ThreadLocal<>();
    public ThreadLocal<AppiumDriver> driverThreadLocal=new ThreadLocal<AppiumDriver>();
    public static WebDriver driver;
    public static String screenshotdir = System.getProperty("user.dir" + "//test-output//screenshots");
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static String requestPayload;
    public static Map<String, String> result = new LinkedHashMap<>();
    public static LinkedHashMap<String, String> appTestData = new LinkedHashMap<>();
    JsonParser jParser;
    public static final String USERNAME = System.getenv("sadhishjay_OAMoP5");
    public static final String AUTOMATE_KEY = System.getenv("ZyautQU7hUDxUSCEG8W4");
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    public static String getScenarioId(String scenarioName) {

        return scenarioName.split(":")[1];
    }

    @SneakyThrows
    public DataRequest getTestData(String featureName, String scenarioName) {
        String path = System.getProperty("user.dir");
        File file = ResourceUtils.getFile(path + "//test-input//testdata.json");
        JsonNode jsonNode = objectMapper.readValue(new String(Files.readAllBytes(file.toPath())), JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            ArrayNode arrayNode = (ArrayNode)
                    jsonNode.findValue(String.valueOf(entry.getKey())).get("scn_ids");
            for (JsonNode node : arrayNode) {
                if (node.toString().contains(getScenarioId(scenarioName))) {
                    ((ObjectNode) entry.getValue()).remove("scn_ids");
                    return objectMapper.readValue(entry.getValue().toString(), DataRequest.class);
                }
            }
        }
        return null;

    }

    @SneakyThrows
    public Map<String, String> getUITestData(String featureName, String scenarioName) {
        JsonFactory jfactory = objectMapper.getFactory();
        String path = System.getProperty("user.dir");
        File file = ResourceUtils.getFile(path + "//test-input//testdata.json");
        JsonNode jsonNode = objectMapper.readValue(new String(Files.readAllBytes(file.toPath())), JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            ArrayNode arrayNode = (ArrayNode)
                    jsonNode.findValue(String.valueOf(entry.getKey())).get("scn_ids");

            for (JsonNode node : arrayNode) {
                if (node.toString().contains(getScenarioId(scenarioName))) {
                    ((ObjectNode) entry.getValue()).remove("scn_ids");
                    jParser=jfactory.createParser(entry.getValue().toString());
                    JsonNode nodes = objectMapper.readTree( jParser);
                    result = objectMapper.
                            convertValue(nodes, new TypeReference<Map<String, String>>(){});
                  return result;

                }
            }
        }

return null;

}
//launch browser
    public static  WebDriver launchBrowser(String browser) {


        if(browser.equalsIgnoreCase("chrome")) {

           // System.setProperty("webdriver.chrome.driver", "/Users/sadhishkumar.thiagarajan/Downloads/chromedriver");
            driver= WebDriverManager.chromedriver().create();
            //driver = new ChromeDriver();

            threadLocal.set(driver);
        }
        else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "/Users/sadhishkumar.thiagarajan/Downloads/geckodriver");
            driver= WebDriverManager.firefoxdriver().create();
           // driver = new FirefoxDriver();
        }
        return threadLocal.get();
    }

    @SneakyThrows
    public LinkedHashMap<String, String> getMobileAppTestData(String featureName,String scenarioName) {
        JsonFactory jsonFactory = objectMapper.getFactory();
        String path = System.getProperty("user.dir");
        File file = ResourceUtils.getFile(path + "//test-input//testdata.json");
        JsonNode jsonNode = objectMapper.readValue(new String(Files.readAllBytes(file.toPath())), JsonNode.class);
        Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            ArrayNode arrayNode = (ArrayNode)
                    jsonNode.findValue(String.valueOf(entry.getKey())).get("scn_ids");
            for (JsonNode node : arrayNode) {
                if (node.toString().contains(getScenarioId(scenarioName))) {
                    ((ObjectNode) entry.getValue()).remove("scn_ids");
                    jParser=jsonFactory.createParser(entry.getValue().toString());
                    JsonNode nodes = objectMapper.readTree( jParser);
                    appTestData = objectMapper.
                            convertValue(nodes, new TypeReference<LinkedHashMap<String, String>>(){});
                    return appTestData;

                }
            }
        }
        return null;
    }

@SneakyThrows
public static String getScreenshot(){
        String screenshot="";
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("ddMMYYYY_HHmmss");
        String sDate=simpleDateFormat.format(date);
        FileUtils.copyFile(src,new File(screenshotdir+"image_"+sDate+".png"));
        byte[] fileContent=FileUtils.readFileToByteArray(src);
        screenshot="data:image/png"+ Base64.getEncoder().encodeToString(fileContent);
        return screenshot;
}


}


