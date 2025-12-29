package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BasePage {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ChangePasswordPage changePasswordPage;

    public ChangePasswordTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        changePasswordPage = new ChangePasswordPage();
    }

    @Test
    public void verifyPasswordChangeFlow() {
        // 1. DATA SETUP (Use Strong Passwords!)
        String email = "security_" + System.currentTimeMillis() + "@test.com";
        String oldPass = "Alpha@123!Secure";
        String newPass = "Beta@456!Secure";

        // 2. Create & Login
        homePage.navigateToRegister();
        registerPage.registerUser("Sec", "Tester", email, "9876543210", oldPass);
        homePage.logout();
        homePage.navigateToLogin();
        loginPage.doLogin(email, oldPass);

        // 3. Change Password
        changePasswordPage = homePage.navigateToChangePassword();
        changePasswordPage.updatePassword(newPass);
        Assert.assertTrue(driver.getTitle().equals("My Account"), "Redirect failed after password change");

        // 4. Logout
        homePage.logout();
        
        // 5. Try Login with NEW Password
        // Wait briefly for the logout to fully clear the session
        try { Thread.sleep(1500); } catch (InterruptedException e) {}
        
        homePage.navigateToLogin();
        loginPage.doLogin(email, newPass);
        
        // 6. Verify success
        Assert.assertTrue(driver.getTitle().equals("My Account"), "New password failed to work!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}