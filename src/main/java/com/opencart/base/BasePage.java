package com.opencart.base;

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

public class BasePage {
    
    public static WebDriver driver;
    public static Properties prop;
    
    // 1. Define the Logger (Static because your init method is static)
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
        
        // 2. Log the start of the test
        logger.info(">> Initializing Browser: " + browserName);

        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            
            // Security & Password Manager Popups Fix
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            
            // Stability Fixes
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            
            // Initialize the Driver
            driver = new ChromeDriver(options);
            logger.info(">> Chrome Driver launched successfully.");
        }
        
        // Global Driver Settings
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        // Navigate
        String url = prop.getProperty("url");
        driver.get(url);
        logger.info(">> Navigated to Application URL: " + url);
    }
}