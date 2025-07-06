package utilities;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class Extentreportmanager implements ITestListener{

	
	public ExtentSparkReporter sparkReporter; //UI of the report --color of the report
	public ExtentReports extent; //Populate common info  -- user, tester name, browserName, project name, module name, environment details
	public ExtentTest test; //creating test case entries in the report and update status of the test methods//updating status of the report.. attaching screenshot
	String repname;
	
	public void onStart(ITestContext context) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repname = "Test-report-" + timeStamp + ".html";
		
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport2.html");
		//sparkReporter = new ExtentSparkReporter("\\.reports\\" + repname);
		sparkReporter.config().setDocumentTitle("Amazon Automation report");// title of Report
		sparkReporter.config().setReportName("Amazon Functional testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "Amazon");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub name", "customer");
		extent.setSystemInfo("Usernam", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");

		String os = context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("operating System", os);

		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
		if (!groups.isEmpty()) {
			extent.setSystemInfo("groups", groups.toString());
		}
	}

	public void onTestStart(ITestResult result) {
		System.out.println("Test started on every method");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entry in report
		test.assignCategory(result.getMethod().getGroups());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got Successfully executed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entry in report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got Failed ");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());// create a new entry in report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got SKIPPED");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
		
		//to open report automatically after test is finished
		
		/*String pathOfExtentReport=System.getProperty("user.dir"+"\\reports\\"+repname);
		File extentReport=new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}*/
	}
}
