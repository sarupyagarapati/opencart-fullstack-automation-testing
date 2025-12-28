package com.opencart.pages;

import com.opencart.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    @FindBy(id = "input-firstname") WebElement firstName;
    @FindBy(id = "input-lastname") WebElement lastName;
    @FindBy(id = "input-email") WebElement email;
    @FindBy(id = "input-telephone") WebElement telephone;
    @FindBy(id = "input-password") WebElement password;
    @FindBy(id = "input-confirm") WebElement confirmPassword;
    @FindBy(name = "agree") WebElement privacyPolicy;
    @FindBy(css = "input.btn.btn-primary") WebElement continueBtn;
    @FindBy(xpath = "//h1[text()='Your Account Has Been Created!']") WebElement successHeader;

    public RegisterPage() { PageFactory.initElements(driver, this); }

    public void registerUser(String fname, String lname, String mail, String phone, String pwd) {
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        email.sendKeys(mail);
        telephone.sendKeys(phone);
        password.sendKeys(pwd);
        confirmPassword.sendKeys(pwd);
        if (!privacyPolicy.isSelected()) privacyPolicy.click();
        continueBtn.click();
    }

    public String getSuccessTitle() { return successHeader.getText(); }
}