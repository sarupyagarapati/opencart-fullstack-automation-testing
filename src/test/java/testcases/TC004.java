package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004 extends BaseClass {

    @Test
    public void verify_logo_visible() {
        // Check if the logo image exists
        boolean logoPresent = driver.findElement(By.id("logo")).isDisplayed();
        Assert.assertTrue(logoPresent);
    }
}