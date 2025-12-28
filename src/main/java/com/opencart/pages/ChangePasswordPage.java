package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage extends BasePage {

    @FindBy(id = "input-password")
    WebElement passwordInput;

    @FindBy(id = "input-confirm")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement continueBtn;
    
    // The success message usually appears on the My Account page after redirect
    @FindBy(css = "div.alert.alert-success")
    WebElement successAlert;

    public ChangePasswordPage() {
        PageFactory.initElements(driver, this);
    }

    public void updatePassword(String newPwd) {
        passwordInput.sendKeys(newPwd);
        confirmPasswordInput.sendKeys(newPwd);
        continueBtn.click();
    }
}