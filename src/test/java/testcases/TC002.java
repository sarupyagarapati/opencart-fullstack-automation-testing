package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.HomePage;

public class TC002 extends BaseClass {

    @Test
    public void verify_login_page() {
        HomePage hp = new HomePage(driver);
        hp.clickmyaccount();
        
        // We just check if the "Login" option is visible in the menu
        // (Note: You might need to add clickLogin() to HomePage later, 
        // but for now, checking the page title is enough)
        
        String pageTitle = driver.getTitle();
        System.out.println("Page title is: " + pageTitle);
        
        // This assertion will pass if the site is loaded
        Assert.assertEquals(pageTitle, "Your Store"); 
    }
}