package demoqa.com.main;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporter {

	public static ExtentReports getReportObject() {
		// ExtentReports, //ExtentSparkReporter Class
		String path = System.getProperty("user.dir") + "//reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Test Results");
		reporter.config().setDocumentTitle("Test Rsults");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Rakesh");
		return extent;
	}

}
