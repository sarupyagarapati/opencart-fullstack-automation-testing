package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderHistoryTest extends BasePage {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    OrderHistoryPage orderHistoryPage;

    public OrderHistoryTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        orderHistoryPage = new OrderHistoryPage();
    }

    @Test
    public void verifyOrderHistoryPageAccess() {
        // 1. Create User & Login (Standard Setup)
        String email = "history_" + System.currentTimeMillis() + "@test.com";
        String pwd = "K@lm!Secure#2025"; 
        
        homePage.navigateToRegister();
        registerPage.registerUser("History", "Checker", email, "9876543210", pwd);
        homePage.logout();
        homePage.navigateToLogin();
        loginPage.doLogin(email, pwd);

        // 2. DIRECTLY Navigate to Order History (Skipping Checkout!)
        orderHistoryPage = homePage.navigateToOrderHistory();

        // 3. Verify Page Title
        Assert.assertEquals(orderHistoryPage.getHeader(), "Order History");
        
        // 4. Verify "Empty" Message
        // OpenCart shows "You have not made any previous orders!" for new users
        String emptyMsg = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(emptyMsg.contains("You have not made any previous orders!"), 
            "Expected empty history message not found!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}