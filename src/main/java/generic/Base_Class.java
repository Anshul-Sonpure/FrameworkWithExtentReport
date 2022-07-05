package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class Base_Class extends FWUtility implements Sys_Constant 
  {   
	 public WebDriver driver;
	 public ExtentTest test;
	 public ExtentReports extent;

	 @BeforeTest
	 public void initialise()
	 { extent = new ExtentReports(System.getProperty("user.dir")+ "/Reports/Report2.html",true);
	 	test = extent.startTest("Valid Login");
	 }
	 @AfterMethod
	 public void getResult(ITestResult result)
	 {
		test.log(LogStatus.PASS, result.getTestName());
		test.log(LogStatus.PASS, result.getName(), "Test Case Passed");
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String snap = FWUtility.capturescreen(driver, "failed");
			test.log(LogStatus.FAIL, result.getTestName());
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(snap));
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			String snap = FWUtility.capturescreen(driver, "skipped");
			test.log(LogStatus.SKIP, result.getTestName());
			test.log(LogStatus.SKIP, result.getThrowable());
			test.log(LogStatus.SKIP, "Snapshot below: " + test.addScreenCapture(snap));
			
		}
		 extent.endTest(test);
	 }
	
 @BeforeTest
 public void OpenApplication()
 { 
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_OUT, TimeUnit.SECONDS);
		driver.get(URL); 
	 
 }
 @AfterTest
 public void TearDown()
 {
     driver.close();
     extent.flush();
	      extent.close();
 }
	
	
  }
