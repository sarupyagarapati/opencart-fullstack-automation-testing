package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    // --- 1. Locators ---
    
    // Top Bar "My Account" Dropdown
    @FindBy(xpath = "//span[text()='My Account']") 
    WebElement myAccountDropdown;

    @FindBy(linkText = "Register") 
    WebElement registerOption;
    
    @FindBy(linkText = "Order History")
    WebElement orderHistoryOption;

    @FindBy(linkText = "Login") 
    WebElement loginOption;

    @FindBy(linkText = "Logout") 
    WebElement logoutOption; 
    
    // Wishlist Link (Top Header)
    @FindBy(id = "wishlist-total") 
    WebElement wishlistLink;

    // Search Bar
    @FindBy(name = "search") 
    WebElement searchBox;

    @FindBy(css = "button.btn.btn-default.btn-lg") 
    WebElement searchBtn;
    
    @FindBy(linkText = "Password")
    WebElement passwordOption;

    // --- 2. Constructor ---
    public HomePage() { 
        PageFactory.initElements(driver, this); 
    }

    // --- 3. Actions ---

    // Navigation Actions
    public void navigateToRegister() {
        myAccountDropdown.click();
        registerOption.click();
    }

    public void navigateToLogin() {
        myAccountDropdown.click();
        loginOption.click();
    }
    
    public void logout() {
        myAccountDropdown.click();
        logoutOption.click();
    }

    // THIS WAS MISSING
    public WishlistPage navigateToWishlist() {
        wishlistLink.click();
        return new WishlistPage();
    }

    // Search Action
    public void performSearch(String product) {
        searchBox.clear();
        searchBox.sendKeys(product);
        searchBtn.click();
    }
    
    public OrderHistoryPage navigateToOrderHistory() {
        myAccountDropdown.click();
        orderHistoryOption.click();
        return new OrderHistoryPage();
    }
    
    public ChangePasswordPage navigateToChangePassword() {
        myAccountDropdown.click();
        passwordOption.click();
        return new ChangePasswordPage();
    }
}