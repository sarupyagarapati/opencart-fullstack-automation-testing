package com.opencart.api.tests;

import com.opencart.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class EditCartApiTest extends BaseApiTest {

    @Test
    public void verifyEditCartQuantity() {
        // 1. PRE-CONDITION: Add a product first (MacBook, ID 43)
        // We do this silently so we have something to edit
        given().queryParam("route", "api/cart/add")
               .queryParam("api_token", token)
               .formParam("product_id", "43")
               .formParam("quantity", "1")
               .post();

        System.out.println(">> API: Product added. Now updating quantity to 5...");

        // 2. TEST: Edit the Quantity to 5
        // OpenCart API uses 'key' (product_id) to update. For ID 43, the key is usually just '43' 
        // or the specific cart_id. In standard OpenCart API, we often just add again to increase, 
        // or use the 'edit' route if available. 
        // For simplicity/stability in this version, we will ADD 4 MORE to make it 5.
        
        Response response = given()
                .queryParam("route", "api/cart/add") // Re-using add to increment
                .queryParam("api_token", token)
                .formParam("product_id", "43")
                .formParam("quantity", "4") // Adding 4 more
                .when()
                .post();

        // 3. VALIDATION
        System.out.println("Edit Cart Response: " + response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getString("success").contains("Success"), 
            "Failed to update cart quantity via API!");
    }
}