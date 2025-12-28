package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class WishlistPage extends BasePage {

    @FindBy(xpath = "//h2[text()='My Wish List']")
    WebElement pageHeader;

    // Gets all product names in the wishlist table
    @FindBy(xpath = "//div[@id='content']//table//tbody//tr//td[@class='text-left']/a")
    List<WebElement> wishlistProducts;

    public WishlistPage() {
        PageFactory.initElements(driver, this);
    }

    public String getHeader() {
        return pageHeader.getText();
    }

    public boolean isProductInWishlist(String productName) {
        for (WebElement product : wishlistProducts) {
            if (product.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }
}