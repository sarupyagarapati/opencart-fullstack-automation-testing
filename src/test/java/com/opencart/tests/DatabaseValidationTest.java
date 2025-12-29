package com.opencart.tests;

import com.opencart.api.base.BaseApiTest;
import com.opencart.utils.DBUtil;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class DatabaseValidationTest extends BaseApiTest {

    @Test
    public void verifyUserRegistrationInDatabase() {
        // 1. Generate Data
        String uniqueEmail = "db_check_" + System.currentTimeMillis() + "@test.com";
        String password = "Password@123";

        Map<String, String> formData = new HashMap<>();
        formData.put("firstname", "DB");
        formData.put("lastname", "Tester");
        formData.put("email", uniqueEmail);
        formData.put("telephone", "1122334455");
        formData.put("password", password);
        formData.put("confirm", password);
        formData.put("agree", "1");

        System.out.println(">> DB TEST: Creating User via API: " + uniqueEmail);

        // 2. Call API to Create User
        RestAssured.given()
                .baseUri(prop.getProperty("url")) // Load URL from API Base
                .queryParam("route", "account/register")
                .formParams(formData)
                .post()
                .then()
                .statusCode(302); // Redirect = Success

        // 3. QUERY DATABASE
        System.out.println(">> DB TEST: Querying MySQL for this user...");
        boolean exists = DBUtil.isUserPresent(uniqueEmail);

        // 4. Assert
        Assert.assertTrue(exists, "❌ CRITICAL FAILURE: User was created by API but NOT found in Database!");
        System.out.println("✅ SUCCESS: User found in Database!");
    }
}