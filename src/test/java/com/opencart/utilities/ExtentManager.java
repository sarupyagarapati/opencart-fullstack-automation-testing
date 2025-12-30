package com.opencart.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            // 1. Create the HTML file path
            String fileName = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
            
            // 2. config the report (Name, Theme, Title)
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
            sparkReporter.config().setReportName("Hybrid Framework Test Results");

            // 3. Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // 4. Add System Info (Shows up in the dashboard)
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}