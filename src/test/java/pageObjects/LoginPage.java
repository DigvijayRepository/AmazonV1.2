package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
   public LoginPage(WebDriver driver)
   {
	   super(driver);
   }
   
   
   //locators
   
   @FindBy(xpath="//input[@aria-label='Enter your mobile number or email']")
   WebElement txtMailorMobNo;
   
   @FindBy(xpath="//input[@aria-labelledby='continue-announce']")
   WebElement button_continue;   
      
   @FindBy(xpath="//input[@id='ap_password'] ")
   WebElement txtPwd;   
         
   @FindBy(xpath="//input[@id='signInSubmit']")
   WebElement button_signIn;   
   
   
   
  
   
   //Actions
   
  public void settxtMailorMobNo(String FirstName)
  {
	  txtMailorMobNo.sendKeys(FirstName);
  }
  
  public void clickButton()
  {
	  button_continue.click();
  }
    
  public void setpwd(String pwd)
  {
	  txtPwd.sendKeys(pwd);
  }      
 
  
  public void clickSignIn()
  {
	  button_signIn.click();
  }
  
  
  
   
}
