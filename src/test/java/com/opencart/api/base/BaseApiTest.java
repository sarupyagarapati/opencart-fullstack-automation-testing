package com.opencart.api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

public class BaseApiTest {

    public Properties prop;
    public String apiUsername;
    public String apiKey;
    
    // --- THIS IS THE MISSING VARIABLE ---
    public String token; 

    @BeforeClass
    public void setup() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) { e.printStackTrace(); }

        // 1. Setup Base URI
        RestAssured.baseURI = prop.getProperty("url") + "index.php";
        apiUsername = prop.getProperty("api_username");
        apiKey = prop.getProperty("api_key");
        
        // 2. AUTO-LOGIN (Generates the token for all child tests)
        Map<String, String> creds = new HashMap<>();
        creds.put("username", apiUsername);
        creds.put("key", apiKey);

        // We capture the token and store it in the public variable 'token'
        token = RestAssured.given()
                .queryParam("route", "api/login")
                .formParams(creds)
                .post()
                .jsonPath().getString("api_token");
                
        System.out.println(">> Base Setup: Generated Global Token: " + token);
    }
}