package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/SearchSentinelReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Framework", "SearchSentinel");
            extent.setSystemInfo("Tester", "Anand");
        }
        return extent;
    }
}
