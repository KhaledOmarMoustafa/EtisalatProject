package org.example;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class PreRequisites {

    static WebDriver delegate;
    static SelfHealingDriver driver;
    static Eyes eyes;

    static String webURL = "https://www.saucedemo.com/v1/index.html";

    public void openBrowser() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");

        // BrowserStack-specific options
        HashMap<String, Object> bstackOptions = new HashMap<>();
  /*      bstackOptions.put("deviceName", "iPhone 11");
        bstackOptions.put("osVersion", "14");*/
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("sessionName", "CloudBasedTesting");
        //   bstackOptions.put("local", "true");
        bstackOptions.put("networkLogs", "true"); // Enable network logs
        // bstackOptions.put("console", "info"); // Enable console logs
        bstackOptions.put("performance", "true");



        capabilities.setCapability("bstack:options", bstackOptions);

        // Initialize driver with corrected capabilities
        delegate = new RemoteWebDriver(
                new URL("https://khaledomar_q9WYvI:idpcEo4tDJ1Y5APuSwsP@hub-cloud.browserstack.com/wd/hub"),
                capabilities
        );
        driver = SelfHealingDriver.create(delegate);


        driver.manage().window().maximize();
        driver.get(webURL);
    }

    public void closeBrowser() {
        driver.quit();
    }


    public void applitoolsVisualTesting(String appName, String testName) {
        Eyes eyes = new Eyes();
        eyes.setApiKey("U98RzzfKD5qsima4msgNP1qnb0ciXkjHFIbTmRLhg11U110"); // Replace with your Applitools API Key
        eyes.open(delegate, appName, testName);

        // Set the match level to LAYOUT to ignore small differences
        eyes.setMatchLevel(MatchLevel.LAYOUT);

        // Automatically accept the first run as baseline
        eyes.setSaveNewTests(true);

        // Capture the entire page
        eyes.check("Full Page Screenshot", com.applitools.eyes.selenium.fluent.Target.window());

        eyes.close();
    }
  /*  public static void setup() {
        // Keep existing cloud-based and Healenium setup intact
        ChromeOptions options = new ChromeOptions();

        // Set up ZAP Proxy without affecting cloud testing
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("localhost:8080"); // OWASP ZAP Proxy Port
        proxy.setSslProxy("localhost:8080");
        options.setProxy(proxy);

        driver = (SelfHealingDriver) new ChromeDriver(options);
    }*/
}
