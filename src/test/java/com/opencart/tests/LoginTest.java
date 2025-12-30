package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
import com.opencart.pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginTest extends BasePage {

    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    WebDriverWait wait;

    public LoginTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization(); // Logs "Initializing Browser" automatically from BasePage
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyUserLogin() {
        // Log the start of the specific test case
        logger.info(">> STARTING TEST: verifyUserLogin");

        // 1. Self-Healing: Create a fresh user
        String tempEmail = "login_" + System.currentTimeMillis() + "@test.com";
        String tempPass = "K$lM#99!xQz_2025";
        
        logger.info(">> Generated fresh credentials: " + tempEmail);

        homePage.navigateToRegister();
        logger.info(">> Navigated to Register Page");

        registerPage.registerUser("Login", "Tester", tempEmail, "9876543210", tempPass);
        logger.info(">> User Registered successfully for Self-Healing");
        
        // 2. Perform Logout
        homePage.logout();
        
        // Wait for logout to complete
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Logout']")));
        logger.info(">> User Logged Out (Pre-condition met)");
        
        // 3. Now Perform the Real Login Test
        homePage.navigateToLogin(); 
        logger.info(">> Navigated to Login Page");

        loginPage.doLogin(tempEmail, tempPass);
        logger.info(">> Performed Login Action");
        
        // 4. Verify
        String actualTitle = driver.getTitle();
        logger.info(">> Verifying Page Title. Found: " + actualTitle);

        Assert.assertTrue(actualTitle.equals("My Account"), 
            "Login Failed! Actual Title: " + actualTitle);
        
        logger.info(">> TEST PASSED: verifyUserLogin");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info(">> Browser Closed");
        }
    }
}