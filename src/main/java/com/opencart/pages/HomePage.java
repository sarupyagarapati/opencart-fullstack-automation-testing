package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    // --- Locators ---
    @FindBy(xpath = "//span[text()='My Account']") 
    WebElement myAccountDropdown;
    
    @FindBy(linkText = "Register") 
    WebElement registerOption;
    
    @FindBy(linkText = "Login") 
    WebElement loginOption;
    
    // THIS WAS MISSING: The Logout Option
    @FindBy(linkText = "Logout") 
    WebElement logoutOption; 
    
    @FindBy(name = "search") 
    WebElement searchBox;
    
    @FindBy(css = "button.btn.btn-default.btn-lg") 
    WebElement searchBtn;

    // --- Constructor ---
    public HomePage() { 
        PageFactory.initElements(driver, this); 
    }

    // --- Actions ---
    public void navigateToRegister() {
        myAccountDropdown.click();
        registerOption.click();
    }

    public void navigateToLogin() {
        myAccountDropdown.click();
        loginOption.click();
    }
    
    // THIS WAS MISSING: The Logout Method
    public void logout() {
        myAccountDropdown.click();
        logoutOption.click();
    }

    public void performSearch(String product) {
        searchBox.clear();
        searchBox.sendKeys(product);
        searchBtn.click();
    }
}