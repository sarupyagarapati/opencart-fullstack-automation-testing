package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003 extends BaseClass {

    @Test
    public void verify_search() {
        // Find search box and type "Mac"
        driver.findElement(By.name("search")).sendKeys("Mac");
        
        // Click search button
        driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        
        // Verify results appear
        boolean isResultDisplayed = driver.findElement(By.linkText("MacBook")).isDisplayed();
        Assert.assertTrue(isResultDisplayed);
    }
}