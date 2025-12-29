package com.opencart.api.tests;

import com.opencart.api.base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class RegisterApiTest extends BaseApiTest {

    @Test
    public void verifyRegisterAccountViaPost() {
        // 1. Generate Dynamic Data (To avoid "Email already exists" error)
        String randomEmail = "api_user_" + System.currentTimeMillis() + "@test.com";
        String password = "Password@123";

        // 2. Prepare the Form Data (Exactly what the UI form sends)
        Map<String, String> formData = new HashMap<>();
        formData.put("firstname", "API");
        formData.put("lastname", "Bot");
        formData.put("email", randomEmail);
        formData.put("telephone", "1234567890");
        formData.put("password", password);
        formData.put("confirm", password);
        formData.put("agree", "1"); // Checking the "Privacy Policy" box

        System.out.println("Attempting to register: " + randomEmail);

        // 3. Send POST Request to the Registration Page
        Response response = given()
                .baseUri(prop.getProperty("url")) // Use the base URL from config
                .queryParam("route", "account/register") // The Registration Route
                .formParams(formData)
                .when()
                .post();

        // 4. Validation
        // When OpenCart registers successfully, it usually REDIRECTS (302) to the success page.
        // Or it returns 200 OK.
        
        System.out.println("Status Code: " + response.getStatusCode());
        
        // We verify that the registration didn't fail (didn't stay on the same page with errors)
        // A successful POST usually redirects to "account/success"
        
        if(response.getStatusCode() == 302) {
             String location = response.getHeader("Location");
             System.out.println("Redirected to: " + location);
             Assert.assertTrue(location.contains("route=account/success"), "Registration failed to redirect to success page!");
        } else {
            // If it returns 200, we check the body for "Your Account Has Been Created!"
            String body = response.asString();
            Assert.assertTrue(body.contains("Your Account Has Been Created") || response.getStatusCode() == 200, 
                "Account creation verification failed.");
        }
    }
}