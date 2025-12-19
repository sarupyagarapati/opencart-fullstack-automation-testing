package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Elements
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement inkMyaccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement inkRegister;

    // Action Methods
    public void clickmyaccount() {
        inkMyaccount.click();
    }

    public void clickregister() {
        inkRegister.click();
    }
    
} // <--- The class ends here, NOT earlier!