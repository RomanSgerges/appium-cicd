package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentReport {
    static ExtentReports extent;
    final static String filePath = "Spark.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public synchronized static ExtentReports getReporter(){
        if (extent == null){
            ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
            spark.config().setDocumentTitle("Appium Framework");
            spark.config().setReportName("Hosha 1");
            spark.config().setTheme(Theme.STANDARD);
            extent = new ExtentReports();
            extent.attachReporter(spark);

        }
        return extent;
    }
    public static synchronized ExtentTest getTest(){
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
    public static synchronized ExtentTest startTest(String testName, String desc){
        ExtentTest test = getReporter().createTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()),test);
        return test;
    }

}
