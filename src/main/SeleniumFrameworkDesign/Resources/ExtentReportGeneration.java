package Resources;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;

public class ExtentReportGeneration {

    public static ExtentReports reportConfig(){
//        ExtentReport, ExtentSparkReporter
        String reportsPathName = System.getProperty("user.dir")+ "/reports/index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportsPathName);
        extentSparkReporter.config().setReportName("Web Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Tester","Swati Radia");
        extent.createTest(reportsPathName);
        return extent;

    }
}
