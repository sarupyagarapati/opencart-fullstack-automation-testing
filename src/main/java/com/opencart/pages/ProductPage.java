package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    @FindBy(id = "button-cart") WebElement addToCartBtn;
    @FindBy(css = "div.alert.alert-success") WebElement successMessage;
    @FindBy(linkText = "shopping cart") WebElement cartLink;
    @FindBy(linkText = "Checkout") WebElement checkoutLink;
 // NEW LOCATOR: The Compare Button (icon with double arrows)
    @FindBy(xpath = "//button[@data-original-title='Compare this Product']")
    WebElement compareBtn;

    // NEW LOCATOR: The Link in the success message "product comparison"
    @FindBy(linkText = "product comparison")
    WebElement comparisonLink;
    @FindBy(xpath = "//button[@data-original-title='Add to Wish List']")
    WebElement addToWishlistBtn;

    public ProductPage() { PageFactory.initElements(driver, this); }

    public void selectProductFromSearch(String productName) {
        driver.findElement(By.linkText(productName)).click();
    }

    public void addToCart() {
        addToCartBtn.click();
    }

    public boolean isProductAdded() {
        return successMessage.isDisplayed();
    }

    public void proceedToCheckout() {
        cartLink.click();
        // Assuming we click "Checkout" button inside the cart page immediately
        driver.findElement(By.xpath("//a[text()='Checkout']")).click();
    }
    public void clickCompareButton() {
        compareBtn.click();
    }
    
    public ComparisonPage clickComparisonLink() {
        comparisonLink.click();
        return new ComparisonPage();
    }
    public void clickAddToWishlist() {
        addToWishlistBtn.click();
    }
}