package com.opencart.api.tests;

import com.opencart.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class CurrencyApiTest extends BaseApiTest {

    @Test
    public void verifyChangeCurrencyToEUR() {
        // 1. Send Request to Change Currency
        Response response = given()
                .queryParam("route", "api/currency") // The Currency Endpoint
                .queryParam("api_token", token)
                .formParam("currency", "EUR") // Change to Euro
                .when()
                .post();

        // 2. Debug
        System.out.println("Currency Response: " + response.asPrettyString());

        // 3. Validation
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getString("success").contains("Success"), 
            "Failed to change currency to EUR!");
            
        // 4. Verify Session Persistence (Optional but cool)
        // We hit the currency endpoint again to see if it stuck? 
        // Or just rely on the success message. The success message confirms the logic worked.
    }
}