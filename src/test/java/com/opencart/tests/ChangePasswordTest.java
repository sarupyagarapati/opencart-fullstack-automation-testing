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
        // 1. DATA SETUP
        String email = "security_" + System.currentTimeMillis() + "@test.com";
        String oldPass = "OldPass@123";
        String newPass = "NewPass@456";

        // 2. Create User & Login
        homePage.navigateToRegister();
        registerPage.registerUser("Sec", "Tester", email, "9876543210", oldPass);
        homePage.logout();
        homePage.navigateToLogin();
        loginPage.doLogin(email, oldPass);

        // 3. Navigate to Change Password
        changePasswordPage = homePage.navigateToChangePassword();
        changePasswordPage.updatePassword(newPass);

        // 4. Verify Success Message (Redirects to My Account)
        // Note: We check the URL or a specific element to confirm redirect
        Assert.assertTrue(driver.getTitle().equals("My Account"), "Redirect failed after password change");

        // 5. NEGATIVE TEST: Logout and try Old Password
        homePage.logout();
        homePage.navigateToLogin();
        loginPage.doLogin(email, oldPass);
        
        // Assert we are STILL on Login Page (Title contains "Login")
        // Or check for the error warning "Warning: No match for E-Mail Address and/or Password."
        Assert.assertTrue(driver.getTitle().equals("Account Login"), "Security Breach: Old password still works!");

        // 6. POSITIVE TEST: Login with New Password
        loginPage.doLogin(email, newPass);
        Assert.assertTrue(driver.getTitle().equals("My Account"), "New password failed to work!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}