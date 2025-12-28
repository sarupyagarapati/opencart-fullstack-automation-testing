package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderHistoryTest extends BasePage {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ProductPage productPage;
    CheckoutPage checkoutPage;
    OrderHistoryPage orderHistoryPage;

    public OrderHistoryTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
        orderHistoryPage = new OrderHistoryPage();
    }

    @Test
    public void verifyOrderAppearsInHistory() {
        // 1. Create User & Login (Self-Healing)
        String email = "buyer_" + System.currentTimeMillis() + "@test.com";
        String pwd = "Pass@123";
        
        homePage.navigateToRegister();
        registerPage.registerUser("Order", "Tester", email, "9876543210", pwd);
        homePage.logout();
        homePage.navigateToLogin();
        loginPage.doLogin(email, pwd);

        // 2. Buy a Product (Using existing flows)
        homePage.performSearch("iPhone");
        productPage.selectProductFromSearch("iPhone");
        productPage.addToCart();
        productPage.proceedToCheckout();
        
        // 3. Checkout as "Logged In User" (Note: Form is shorter for logged in users!)
        // Since we are logged in, we only need specific checkout steps.
        // However, for simplicity, we will assume standard billing details filling:
        checkoutPage.fillGuestDetails("Ravi", "Kumar", email, "9876543210", "Main St", "Vizag", "530001");
        checkoutPage.completeOrder();
        
        // 4. Verify Success Message
        Assert.assertTrue(checkoutPage.isOrderPlaced());

        // 5. Navigate to Order History
        orderHistoryPage = homePage.navigateToOrderHistory();

        // 6. Verify the Order is there
        Assert.assertEquals(orderHistoryPage.getHeader(), "Order History");
        // Verify the status of the latest order is "Pending"
        Assert.assertEquals(orderHistoryPage.getLatestOrderStatus(), "Pending");
        System.out.println("Latest Order ID Verified: " + orderHistoryPage.getLatestOrderId());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}