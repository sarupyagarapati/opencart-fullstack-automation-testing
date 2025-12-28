package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.CheckoutPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GuestCheckoutTest extends BasePage {

    HomePage homePage;
    ProductPage productPage;
    CheckoutPage checkoutPage;

    public GuestCheckoutTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        productPage = new ProductPage();
        checkoutPage = new CheckoutPage();
    }

    @Test
    public void verifyGuestCheckoutFlow() {
        homePage.performSearch("iPhone");
        productPage.selectProductFromSearch("iPhone");
        productPage.addToCart();
        productPage.proceedToCheckout();
        
        checkoutPage.selectGuestCheckout();
        checkoutPage.fillGuestDetails("Guest", "User", "guest@test.com", "9876543210", "Vizag Road", "Visakhapatnam", "530001");
        checkoutPage.completeOrder();
        
        Assert.assertTrue(checkoutPage.isOrderPlaced(), "Order success message missing!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}