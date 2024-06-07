package utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static final ExtentReports extentReports;

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("UltimateQA Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project Name", "UltimateQA");
        extentReports.setSystemInfo("Author", "Muhammad Faizan");
    }

    public static synchronized ExtentReports getExtentReports() {
        return extentReports;
    }




}
