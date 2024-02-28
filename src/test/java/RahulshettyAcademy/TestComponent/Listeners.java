package RahulshettyAcademy.TestComponent;

import org.testng.ITestListener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RahulshettyAcademy.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports extent= ExtentReporterNG.getReportObject();
	
	 ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();    //thread safe
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test= extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);  //unique thread id(Error validation)-->test
		
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		//test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());
		
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			String filePath = null;
			try {
				filePath = getScreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//screenshot,attach to report
		
		
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	

}
