package com.opencart.base;

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

    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            
            // 1. DATA BREACH & PASSWORD FIXES
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            
            // 2. THE CRASH FIX: Start maximized via arguments, NOT via driver.manage()
            options.addArguments("--start-maximized");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");
            
            driver = new ChromeDriver(options);
        }
        
        // REMOVED: driver.manage().window().maximize(); <--- THIS WAS CAUSING THE CRASH
        
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(prop.getProperty("url"));
    }
}