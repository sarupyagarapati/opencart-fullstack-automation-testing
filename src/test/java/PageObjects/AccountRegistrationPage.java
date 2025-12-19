package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstname;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtlastname;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtemail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement telephone;

@FindBy(xpath="//input[@id='input-password']")
WebElement password;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement confirm;


@FindBy(xpath = "//input[@name='agree']") 
WebElement policy;

@FindBy(xpath = "//input[@value='Continue']") 
WebElement Continue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgconfirmation;

public void setFirstName(String fname) {
	txtFirstname.sendKeys(fname);
}

public void setLastName(String lname) {
	txtlastname.sendKeys(lname);
}

public void setEmail(String Email) {
	txtemail.sendKeys(Email);
}

public void setphone(String tel) {
	telephone.sendKeys(tel);
}

public void setpassword(String pwd) {
	password.sendKeys(pwd);
}

public void setconfirm(String con) {
	confirm.sendKeys(con);
}


public void setpolicy()
{
	policy.click();
}
public void clickContinue() {
	Continue.click();
}

public String getConfirmationmsg() {try {
	return (msgconfirmation.getText());
	
	
}
catch (Exception e) {
	return (e.getMessage());
}
	
}



}
