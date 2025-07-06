package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountLoginTest extends BaseClass {

	@Test(groups={"Master","sanity"})
	public void Verify_Account_Registartion() throws InterruptedException {

		logger.info("***** STarting TC_01_Verify_Account_Registartion  *****");

		try {
			//HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("***** hovered on Account  *****");
			hp.clicklnkSignIn();
			logger.info("***** clicked on signIn  *****");
			Thread.sleep(4000);
            
			
			//LoginPage
			LoginPage arp = new LoginPage(driver);
			logger.info("***** Started  Login  *****");
			arp.settxtMailorMobNo(p.getProperty("username"));
			arp.clickButton();
			arp.setpwd(p.getProperty("pwd"));
			arp.clickSignIn();
			logger.info("***** Logged in  *****");
            
			
			//MyAccountPage
			logger.info("***** validating Expected message  *****");
			MyAccountPage macp=new MyAccountPage(driver);
			String confirmationMsg = macp.getConfirmationMsg();
			Assert.assertEquals(confirmationMsg, "Hello, digvijay");
		}

		catch (Exception e) {
			logger.error("Test failed..");
			Assert.fail();
		}
		    logger.info("***** Finished  *****");
	}

}

//common lang pom dependancy

/*
 * arp.settxtMailorMobNo(randomString().toUpperCase()); arp.clickButton();
 * arp.setpwd(randomString().toUpperCase()); arp.setEmail(randomString() +
 * "@gmail.com");
 * 
 * String password = randomAlphaNumeric();
 * 
 * arp.setpwd(password);
 * 
 * arp.clickSignIn(); arp.clickButton();
 * 
 * String confirmationMsg = arp.getConfirmationMsg();
 * 
 * Assert.assertEquals(confirmationMsg, "Your Account Has Been Created!");
 * 
 * }
 * 
 * public String randomString() { String generatedString =
 * RandomStringUtils.randomAlphabetic(8); return generatedString; }
 * 
 * public String randomAlphaNumeric() { String AlphaNumeric =
 * RandomStringUtils.randomAlphanumeric(8); return AlphaNumeric; }
 */