package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005 extends BaseClass {

    @Test
    public void verify_currency_button() {
        // Check if Currency dropdown is present
        boolean currencyButton = driver.findElement(By.xpath("//span[text()='Currency']")).isDisplayed();
        Assert.assertTrue(currencyButton);
    }
}