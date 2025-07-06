package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups= {"sanity","Master","dataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		
		p=new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());//log4j2
        
		switch(br.toLowerCase())
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
		default:System.out.println("Invalid browser");return;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2000));
		driver.get(p.getProperty("appURL")); //Reading Url from properties file
		driver.manage().window().maximize();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}

	public String randomAlphaNumeric() {
		String AlphaNumeric = RandomStringUtils.randomAlphanumeric(8);
		return AlphaNumeric;
	}

	@AfterClass(groups= {"sanity","Master","dataDriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException
	{
	    
		//String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
		TakesScreenshot ts= (TakesScreenshot) driver;
		File sourceFile=ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath="\\.reports\\screenshots"+tname;
		File dest=new File(targetFilePath);
		
		
		
		sourceFile.renameTo(dest);
		
		return targetFilePath;
	}

}
