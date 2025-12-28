package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "input-email") WebElement emailInput;
    @FindBy(id = "input-password") WebElement passwordInput;
    @FindBy(xpath = "//input[@value='Login']") WebElement loginBtn;

    public LoginPage() { PageFactory.initElements(driver, this); }

    public void doLogin(String un, String pwd) {
        emailInput.sendKeys(un);
        passwordInput.sendKeys(pwd);
        loginBtn.click();
    }
}