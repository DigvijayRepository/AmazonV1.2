package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC02_AccountLoginUsingDataProvider extends BaseClass {
	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups={"dataDriven","Master"})//getting dataprovider from differnt class
	public void Verify_LoginAccountUsingDataProvider(String email,String pwd,String exp) throws InterruptedException {

		logger.info("***** STarting TC02_AccountLoginUsingDataProvider  *****");

		try
		{
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
			System.out.println(email);
			arp.settxtMailorMobNo(email);
			arp.clickButton();
			arp.setpwd(pwd);
			arp.clickSignIn();
			logger.info("***** Logged in  *****");            
			
			//MyAccountPage
			logger.info("***** validating Expected message  *****");
			MyAccountPage macp=new MyAccountPage(driver);
			
			String confirmationMsg = macp.getConfirmationMsg();	
			
			
			/*dataisvalid-loginsucess-test pass -logout
			              loginfailed-test fail
			
			 dataisInvalid-loginsucess-test fail -logout
			              loginfailed-test Pass */
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(confirmationMsg.equals("Hello, digvijay"))
				{
					hp.clickMyAccount();
					macp.clicklnk_signout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(confirmationMsg.equals("Hello, digvijay"))
				{
					macp.clicklnk_signout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		
			logger.info("***** Finished TC02_AccountLoginUsingDataProvider *****");
	}


	

}
