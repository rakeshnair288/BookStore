package TestComponents;

import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import demoqa.com.main.ExtendReporter;

public class Listeners extends ReUsableMethods implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtendReporter.getReportObject();

	@Override

	public void onTestStart(ITestResult result) {

		// entry for each test
		test = extent.createTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {

		test.fail(result.getThrowable());
		try {
			driver = (ChromeDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(filePath, filePath);
	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
