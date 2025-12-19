package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006 extends BaseClass {

    @Test
    public void verify_contact_us_link() {
        // Click Contact Us at the bottom
        driver.findElement(By.linkText("Contact Us")).click();
        
        // Verify we are on the contact page
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Contact Us");
    }
}