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
    @FindBy(id = "input-payment-address-1") WebElement address;
    @FindBy(id = "input-payment-city") WebElement city;
    @FindBy(id = "input-payment-postcode") WebElement zip;
    @FindBy(id = "input-payment-country") WebElement countryDropdown;
    @FindBy(id = "input-payment-zone") WebElement zoneDropdown;
    @FindBy(id = "input-payment-email") WebElement email;
    @FindBy(id = "input-payment-telephone") WebElement phone;

    // Buttons
    @FindBy(id = "button-guest") WebElement continueGuestBtn;
    @FindBy(id = "button-payment-address") WebElement continueBillingBtn; 
    
    // NEW LOCATOR: Step 3 (Delivery Details) Button
    @FindBy(id = "button-shipping-address") WebElement continueShippingAddressBtn;
    
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
        fillAddressLogic(a, c, z);
        continueGuestBtn.click();
    }

    public void fillLoggedInBillingDetails(String f, String l, String a, String c, String z) {
        wait.until(ExpectedConditions.visibilityOf(fn)).sendKeys(f);
        ln.sendKeys(l);
        fillAddressLogic(a, c, z);
        continueBillingBtn.click();
    }

    // NEW METHOD: Handle Step 3
    public void proceedToDeliveryDetails() {
        // Wait for the button to be clickable (Step 3)
        wait.until(ExpectedConditions.elementToBeClickable(continueShippingAddressBtn)).click();
    }

    private void fillAddressLogic(String a, String c, String z) {
        address.sendKeys(a);
        city.sendKeys(c);
        zip.sendKeys(z);
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("India");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='input-payment-zone']/option"), 1));
        Select zoneSelect = new Select(zoneDropdown);
        zoneSelect.selectByVisibleText("Andhra Pradesh");
    }

    public void completeOrder() {
        // Step 4: Shipping Method
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodBtn)).click();
        
        // Step 5: Payment Method
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox));
        if(!termsCheckbox.isSelected()) termsCheckbox.click();
        paymentMethodBtn.click();
        
        // Step 6: Confirm
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderBtn)).click();
    }
    
    public boolean isOrderPlaced() {
        wait.until(ExpectedConditions.visibilityOf(successMsg));
        return successMsg.isDisplayed();
    }
}