package com.opencart.base;  // <--- This line is CRITICAL

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BasePage {  // <--- This line is CRITICAL

    public static WebDriver driver;
    public static Properties prop;
    public static Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        logger.info(">> Initializing Browser: " + browserName);

        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // 1. Basic Preferences
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            // 2. Performance Options
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");

            // 3. HEADLESS MODE CONFIGURATION
            options.addArguments("--headless=new"); 
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);
            logger.info(">> Chrome Driver launched in HEADLESS mode (1920x1080).");
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(prop.getProperty("url"));
        logger.info(">> Navigated to Application URL: " + prop.getProperty("url"));
    }
}