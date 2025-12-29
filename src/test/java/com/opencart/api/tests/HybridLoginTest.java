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

    // DECLARE ONLY - DO NOT use 'new' here
    HomePage homePage;
    LoginPage loginPage;
    String autoEmail;
    String autoPassword;

    public HybridLoginTest() { super(); }

    @BeforeMethod
    public void setupDataAndBrowser() {
        // 1. Open Browser FIRST
        initialization(); 
        
        // 2. NOW initialize Page Objects (They will pick up the open driver)
        homePage = new HomePage();
        loginPage = new LoginPage();

        // 3. API Data Creation
        System.out.println(">> HYBRID TEST: Seeding data via API...");
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

        RestAssured.baseURI = prop.getProperty("url") + "index.php";
        RestAssured.given()
                .queryParam("route", "account/register")
                .formParams(formData)
                .post()
                .then()
                .statusCode(302);
    }

    @Test
    public void verifyLoginWithApiCreatedUser() {
        System.out.println(">> HYBRID TEST: Logging in via Selenium...");
        homePage.navigateToLogin();
        loginPage.doLogin(autoEmail, autoPassword);
        Assert.assertTrue(driver.getTitle().equals("My Account"), "Hybrid Login Failed!");
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}