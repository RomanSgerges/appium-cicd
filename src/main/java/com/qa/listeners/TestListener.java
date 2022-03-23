package com.qa.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.BaseTest;
import com.qa.reports.ExtentReport;
import com.qa.utils.TestUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;


public class TestListener implements ITestListener {
    TestUtils utils = new TestUtils();

    // method on Test to handle the exceptions
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            result.getThrowable().printStackTrace(pw);
            System.out.println(sw.toString());
        }
        // method of screenshot capture
        BaseTest base = new BaseTest();
        File file = base.getDriver().getScreenshotAs(OutputType.FILE);

        // convert the file object to the base64 string
        byte[] encoded = null;
        try {
            encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Map<String, String> params = new HashMap<String, String>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

        // create folder and add the screenshot image as png
        String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" + params.get("deviceName")
                + File.separator + base.getDateTime() + File.separator + result.getTestClass().getRealClass()
                .getSimpleName() + File.separator + result.getName() + ".png";

        // add the screenshots to the test report file
        String completeImagePath = /*System.getProperty("user.dir") +*/ File.separator + imagePath;
        try {
            FileUtils.copyFile(file, new File(imagePath));
            Reporter.log("this is simple screenshot");
            Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath + "'height='200' width='200'/> </a>");
        } catch (IOException e) {
            // auto generate catch block
            e.printStackTrace();
        }

            ExtentReport.getTest().fail("Test Failed",
                    MediaEntityBuilder.createScreenCaptureFromPath(completeImagePath).build());
            ExtentReport.getTest().fail("Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());

          ExtentReport.getTest().fail(result.getThrowable());


    } // end of the on onTestFailure method


    // start on methods
    @Override
    public void onTestStart(ITestResult result){
        BaseTest base = new BaseTest();
        ExtentReport.startTest(result.getName(),result.getMethod().getDescription())
                .assignAuthor("Romany.G");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        ExtentReport.getTest().log(Status.PASS,"Test Passed");
        ExtentReport.getReporter().flush();
    }


    @Override
    public void onTestSkipped(ITestResult result){
        ExtentReport.getTest().log(Status.SKIP,"Test Skipped");
        ExtentReport.getReporter().flush();
    }

    @Override
    public void onFinish(ITestContext context){
        //ExtentReport.getTest().log(Status.SKIP,"Test Skipped");
        ExtentReport.getReporter().flush();
    }

    }
