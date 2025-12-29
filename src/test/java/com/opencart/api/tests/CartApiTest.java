package com.opencart.api.tests;

import com.opencart.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class CartApiTest extends BaseApiTest {

    @Test
    public void verifyAddItemToCart() {
        // 1. Send Request
        // We need: Route, Token, ProductID, Quantity
        Response response = given()
                .queryParam("route", "api/cart/add")
                .queryParam("api_token", token) // USE THE GLOBAL TOKEN
                .formParam("product_id", "43")  // 43 = MacBook in OpenCart
                .formParam("quantity", "1")
                .when()
                .post();

        // 2. Print Response
        System.out.println("Add To Cart Response: " + response.asPrettyString());

        // 3. Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        
        // Check for the success message
        String successMsg = response.jsonPath().getString("success");
        Assert.assertTrue(successMsg.contains("Success: You have modified your shopping cart!"), 
                "Cart add failed!");
    }
}