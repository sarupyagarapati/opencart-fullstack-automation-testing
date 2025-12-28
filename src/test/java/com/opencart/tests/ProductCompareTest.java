package com.opencart.tests;

import com.opencart.base.BasePage;
import com.opencart.pages.ComparisonPage;
import com.opencart.pages.HomePage;
import com.opencart.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductCompareTest extends BasePage {

    HomePage homePage;
    ProductPage productPage;
    ComparisonPage comparisonPage;

    public ProductCompareTest() { super(); }

    @BeforeMethod
    public void setup() {
        initialization();
        homePage = new HomePage();
        productPage = new ProductPage();
        comparisonPage = new ComparisonPage();
    }

    @Test
    public void verifyTwoProductsComparison() {
        // 1. Add First Product (MacBook)
        homePage.performSearch("MacBook");
        productPage.selectProductFromSearch("MacBook");
        productPage.clickCompareButton();
        
        // 2. Add Second Product (iPhone)
        homePage.performSearch("iPhone");
        productPage.selectProductFromSearch("iPhone");
        productPage.clickCompareButton();
        
        // 3. Go to Comparison Page
        // (We wait a second for the success message to update with the link)
        try { Thread.sleep(1000); } catch (Exception e) {} 
        comparisonPage = productPage.clickComparisonLink();
        
        // 4. Verify Both exist in the table
        Assert.assertEquals(comparisonPage.getHeader(), "Product Comparison");
        Assert.assertTrue(comparisonPage.isProductInComparison("MacBook"), "MacBook missing from comparison!");
        Assert.assertTrue(comparisonPage.isProductInComparison("iPhone"), "iPhone missing from comparison!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}