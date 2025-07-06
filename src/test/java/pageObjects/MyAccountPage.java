package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	// Locators

	@FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
	WebElement msgConfirmation;

	@FindBy(xpath = "//span[text()='Sign Out']")
	WebElement lnk_signout;

	public String getConfirmationMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void clicklnk_signout() {
		lnk_signout.click();
	}

}
