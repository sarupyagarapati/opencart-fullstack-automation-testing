package com.opencart.api.tests;

import com.opencart.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class LoginApiTest extends BaseApiTest {

    @Test
    public void verifyApiLogin() {
        // 1. Prepare Data
        Map<String, String> formData = new HashMap<>();
        formData.put("username", apiUsername);
        formData.put("key", apiKey);

        // 2. Send POST Request
        Response response = given()
                .queryParam("route", "api/login") // The endpoint
                .formParams(formData)             // The credentials
                .when()
                .post();                          // Action

        // 3. Debug: Print Response
        System.out.println("API Response: " + response.asPrettyString());

        // 4. Validate
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // OpenCart returns a specific "api_token" field on success
        String token = response.jsonPath().getString("api_token");
        Assert.assertNotNull(token, "Token is null! Login failed.");
        
        System.out.println("Generated Token: " + token);
    }
}