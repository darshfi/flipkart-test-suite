package com.qaproject.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;// one instance shared across all tests
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();// each test have their own threads, so no overwriting

    public static ExtentReports getInstance() {
        if (extent == null) {// make sure only one HTML file is created
            // HTML file and set it's title and heading
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/TestReport.html");
            reporter.config().setDocumentTitle("Flipkart QA Test Suite");
            reporter.config().setReportName("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            // adding metadata to the file
            extent.setSystemInfo("Tester", "Darsh Patel");
            extent.setSystemInfo("Environment", "Flipkart.com");
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void flush() {
        if (extent != null) extent.flush();
    }
}
