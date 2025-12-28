package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage extends BasePage {
    
    WebDriverWait wait;

    // Guest Options
    @FindBy(xpath = "//input[@value='guest']") WebElement guestRadioBtn;
    @FindBy(id = "button-account") WebElement continueAccountBtn;

    // Billing Details
    @FindBy(id = "input-payment-firstname") WebElement fn;
    @FindBy(id = "input-payment-lastname") WebElement ln;
    @FindBy(id = "input-payment-email") WebElement email;
    @FindBy(id = "input-payment-telephone") WebElement phone;
    @FindBy(id = "input-payment-address-1") WebElement address;
    @FindBy(id = "input-payment-city") WebElement city;
    @FindBy(id = "input-payment-postcode") WebElement zip;
    
    // Dropdowns
    @FindBy(id = "input-payment-country") WebElement countryDropdown;
    @FindBy(id = "input-payment-zone") WebElement zoneDropdown;
    
    // Buttons
    @FindBy(id = "button-guest") WebElement continueGuestBtn;
    @FindBy(id = "button-shipping-method") WebElement shippingMethodBtn;
    @FindBy(name = "agree") WebElement termsCheckbox;
    @FindBy(id = "button-payment-method") WebElement paymentMethodBtn;
    @FindBy(id = "button-confirm") WebElement confirmOrderBtn;
    @FindBy(xpath = "//h1[text()='Your order has been placed!']") WebElement successMsg;

    public CheckoutPage() {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectGuestCheckout() {
        wait.until(ExpectedConditions.visibilityOf(guestRadioBtn)).click();
        continueAccountBtn.click();
    }

    public void fillGuestDetails(String f, String l, String e, String p, String a, String c, String z) {
        wait.until(ExpectedConditions.visibilityOf(fn)).sendKeys(f);
        ln.sendKeys(l);
        email.sendKeys(e);
        phone.sendKeys(p);
        address.sendKeys(a);
        city.sendKeys(c);
        zip.sendKeys(z);
        
        // 1. Select Country (India)
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("India");
        
        // 2. PROFESSIONAL WAIT: Wait until the State dropdown has more than 1 option
        // This ensures the AJAX call is finished and states are loaded.
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='input-payment-zone']/option"), 1));
        
        // 3. Select State (Andhra Pradesh)
        Select zoneSelect = new Select(zoneDropdown);
        zoneSelect.selectByVisibleText("Andhra Pradesh");
        
        continueGuestBtn.click();
    }

    public void completeOrder() {
        // Step 1: Shipping Method
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodBtn)).click();
        
        // Step 2: Payment Method (Wait for Terms checkbox to be clickable)
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox));
        if(!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
        paymentMethodBtn.click();
        
        // Step 3: Confirm
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
    }
    
    public boolean isOrderPlaced() {
        wait.until(ExpectedConditions.visibilityOf(successMsg));
        return successMsg.isDisplayed();
    }
}