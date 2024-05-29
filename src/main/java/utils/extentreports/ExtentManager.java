package utils.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static final ExtentReports extentReports;

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("ZConnect Extent Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project Name", "ZConnect");
        extentReports.setSystemInfo("Author", "Muhammad Faizan Zubair");
    }

    public static synchronized ExtentReports getExtentReports() {
        return extentReports;
    }

    /*
    public synchronized static ExtentReports createExtentReports(){
        if(extentReports==null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
            reporter.config().setReportName("ZConnect Extent Report");
            extentReports.attachReporter(reporter);
            extentReports.setSystemInfo("Project Name", "ZConnect");
            extentReports.setSystemInfo("Author", "Muhammad Faizan Zubair");
        }
        return extentReports;

    }

     */


}
