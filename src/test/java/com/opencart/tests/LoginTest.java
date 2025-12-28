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
        initialization();
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifyUserLogin() {
        // 1. Self-Healing: Create a fresh user
        String tempEmail = "login_" + System.currentTimeMillis() + "@test.com";
        String tempPass = "K$lM#99!xQz_2025";
        
        homePage.navigateToRegister();
        registerPage.registerUser("Login", "Tester", tempEmail, "9876543210", tempPass);
        
        // 2. Perform Logout
        homePage.logout();
        
        // --- THE FIX --- 
        // We explicitly wait until the "Account Logout" header appears.
        // This ensures the user is 100% logged out before we try to log back in.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Logout']")));
        
        // 3. Now Perform the Real Login Test
        homePage.navigateToLogin(); // This will now correctly find the "Login" link
        loginPage.doLogin(tempEmail, tempPass);
        
        // 4. Verify
        Assert.assertTrue(driver.getTitle().equals("My Account"), 
            "Login Failed! Actual Title: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}