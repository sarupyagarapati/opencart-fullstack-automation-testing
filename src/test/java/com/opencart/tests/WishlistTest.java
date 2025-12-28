package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WishlistTest extends BasePage {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ProductPage productPage;
    WishlistPage wishlistPage;

    public WishlistTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        productPage = new ProductPage();
        wishlistPage = new WishlistPage();
    }

    @Test
    public void verifyAddProductToWishlist() {
        // 1. PRE-CONDITION: Create User & Login
        String email = "wishlist_" + System.currentTimeMillis() + "@test.com";
        String pwd = "StrongPassword@2025";
        
        homePage.navigateToRegister();
        registerPage.registerUser("Wish", "Tester", email, "9876543210", pwd);
        homePage.logout();
        
        homePage.navigateToLogin();
        loginPage.doLogin(email, pwd);

        // 2. Search & Select Product (CHANGED TO MACBOOK - SAFER DATA)
        homePage.performSearch("MacBook");
        productPage.selectProductFromSearch("MacBook");

        // 3. Add to Wishlist
        productPage.clickAddToWishlist();
        
        // 4. Navigate to Wishlist Page
        try { Thread.sleep(2000); } catch (Exception e) {}
        wishlistPage = homePage.navigateToWishlist();

        // 5. Verify
        Assert.assertEquals(wishlistPage.getHeader(), "My Wish List");
        Assert.assertTrue(wishlistPage.isProductInWishlist("MacBook"), "Product not found in wishlist!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}