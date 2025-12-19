package testcases;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

    public WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://localhost/opencartsite");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    // --- FIX 1: Changed 'private' to 'public' so TC001 can use them ---
    
    public String randomAlphaNumeric() {
        String generatedString = RandomStringUtils.randomAlphanumeric(6);
        return generatedString;
    }

    // --- FIX 2: Changed return type from 'Object' to 'String' ---
    public String randomNumber() {
        String generatedString = RandomStringUtils.randomNumeric(10);
        return generatedString;
    }

    public String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }
}