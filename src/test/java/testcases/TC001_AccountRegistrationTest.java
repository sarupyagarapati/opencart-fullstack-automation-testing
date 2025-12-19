package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
	
@Test
public void verify_account_registration() {
	HomePage hp=new HomePage(driver);
	hp.clickmyaccount();
	hp.clickregister();
	
	AccountRegistrationPage regpage= new AccountRegistrationPage(driver) ;
	regpage.setFirstName(randomString().toUpperCase());
	regpage.setLastName(randomString().toUpperCase());
	regpage.setEmail(randomString()+"@gmail.com");
	regpage.setphone("3566548765432");
   regpage.setpassword("erwhjk5437892");
   regpage.setconfirm("erwhjk5437892");
   regpage.setpolicy();
   regpage.clickContinue();
   String confmsg=regpage.getConfirmationmsg();
   Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	
	
}

}
