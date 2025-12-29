package com.opencart.api.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.HomePage;
import com.opencart.pages.LoginPage;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class HybridLoginTest extends BasePage {

    HomePage homePage;
    LoginPage loginPage;
    String autoEmail;
    String autoPassword;

    public HybridLoginTest() { super(); }

    @BeforeMethod
    public void setupDataAndBrowser() {
        // --- STEP 1: API PHASE (Create Data) ---
        System.out.println(">> HYBRID TEST: Seeding data via API...");
        
        
        
        // 2. Prepare Unique Data
        autoEmail = "hybrid_" + System.currentTimeMillis() + "@test.com";
        autoPassword = "Password@123";

        Map<String, String> formData = new HashMap<>();
        formData.put("firstname", "Hybrid");
        formData.put("lastname", "Tester");
        formData.put("email", autoEmail);
        formData.put("telephone", "1234567890");
        formData.put("password", autoPassword);
        formData.put("confirm", autoPassword);
        formData.put("agree", "1");

        // 3. Hit the API to Register
        RestAssured.baseURI = prop.getProperty("url") + "index.php";
        RestAssured.given()
                .queryParam("route", "account/register")
                .formParams(formData)
                .post()
                .then()
                .statusCode(302); // Expect Redirect (Success)

        System.out.println(">> DATA CREATED: " + autoEmail);
        
        // --- STEP 2: UI SETUP ---
        // (Browser is already open from initialization())
        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test
    public void verifyLoginWithApiCreatedUser() {
        System.out.println(">> HYBRID TEST: Logging in via Selenium...");
        
        // 1. Go to Login Page
        homePage.navigateToLogin();
        
        // 2. Login with the API-Created Credentials
        loginPage.doLogin(autoEmail, autoPassword);
        
        // 3. Verify Login Success
        Assert.assertTrue(driver.getTitle().equals("My Account"), "Hybrid Login Failed!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}