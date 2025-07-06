package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	

	public HomePage(WebDriver driver) 
	{
     super(driver);
	}

	@FindBy(xpath = "//div[@id='nav-link-accountList']")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//span[text()='Sign in']")
	WebElement lnkSignIn;
	
	

	public void clickMyAccount() 
	{
		Actions a=new Actions(driver);
		a.moveToElement(lnkMyAccount).perform();
	}
	
	public void clicklnkSignIn() 
	{
		lnkSignIn.click();
	}	
	

}
