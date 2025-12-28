package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.HomePage;
import com.opencart.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends BasePage {

    HomePage homePage;
    RegisterPage registerPage;

    public RegisterTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        registerPage = new RegisterPage();
    }

    @Test
    public void verifyUserRegistration() {
        homePage.navigateToRegister();
        // Dynamic email ensures this test runs 1000 times without failure
        String dynamicEmail = "user_" + System.currentTimeMillis() + "@test.com"; 
        registerPage.registerUser("Ravi", "Kumar", dynamicEmail, "9876543210", "Test@123");
        Assert.assertEquals(registerPage.getSuccessTitle(), "Your Account Has Been Created!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}