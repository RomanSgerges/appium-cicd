package com.qa;
import com.qa.utils.TestUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class BaseTest {

    protected static AppiumDriver driver;
    protected static Properties props;
    protected static HashMap<String, String> strings = new HashMap<String, String>();
    protected static String dateTime;
    protected static AppiumDriverLocalService server;
    InputStream inputStream;
    InputStream stringsis;
    TestUtils utils;
    // log4j




    // create the instructor
    public BaseTest(){

        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


    // add the video recorder
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Super before method");
        ((CanRecordScreen) driver).startRecordingScreen();
    }
    // continue to add video after the method
    @AfterMethod
    public void afterMethod(ITestResult result){
        System.out.println("Super after method");
      String media = ((CanRecordScreen) driver).stopRecordingScreen();

      // make if condition to capture video only in fail test cases
      if(result.getStatus() == 2){
          Map <String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
          // create the folder and take the parameters, device name,version,platform from the testng xml.
          String dir = "videos" + File.separator + params.get("platformName") + "_" +params.get("platformVersion")+"_" + params.get("deviceName")+ File.separator + dateTime + File.separator + result.getTestClass().getRealClass().getSimpleName();

          // create new video folder if it's not exist
          File videoDir = new File(dir);
          if(!videoDir.exists()){
              videoDir.mkdirs();
          }
          try {
              FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
              stream.write(Base64.decode(media));
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

    }

    // get the appium server
    @BeforeSuite
    public void beforeSuite(){
        server = getAppiumServerDefault();
        server.start();
        server.clearOutPutStreams();
       System.out.println("Appium server started");
    }
    @AfterSuite
    public void afterSuite(){
        server.stop();
        utils.log().info("Appium server stopped");
    }
    // method to get the driver appium LS object
    public AppiumDriverLocalService getAppiumServerDefault(){
        return AppiumDriverLocalService.buildDefaultService();
    }


    @Parameters({"platformName", "deviceName"})
    // start the class from here (its works before the test cases).

    @BeforeTest
    public void beforeTest(String platformName,String deviceName) throws Exception {

        utils = new TestUtils();
        dateTime = utils.getDateTime();


        try {
            props = new Properties();
            // assign the configFile to the config.properties file.
            String propFileName = "config.properties";
            String xmlFileName = "strings/strings.xml";

            // read the files
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            utils = new TestUtils();
            strings = utils.parseStringXML(stringsis);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", platformName);
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("automationName", props.getProperty("androidAutomationName"));

            // launch the app with specific activity by using app package and activity
            caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
            caps.setCapability("appActivity", props.getProperty("androidAppActivity"));

            caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");

            // launch the emulator automatic
            caps.setCapability("avd", "Pixel_3");
            caps.setCapability("avdLaunchTimeout", 18000);

            // the path for the server
            URL url = new URL (props.getProperty("appiumURL"));
            // the path for the apk
            URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
            utils.log().info("app "+ url);

            driver = new AndroidDriver(url, caps);
            String sessionId= driver.getSessionId().toString();


        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
            if (stringsis !=null){
                stringsis.close();
            }
        }
    }
    // method to get the driver for testListener use
    public AppiumDriver getDriver(){
        return driver;
    }

    // method to return datetime
    public String getDateTime(){
        return dateTime;

    };


    // method of check element visibility
    public void waitForVisibility(MobileElement e){
       WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }

    //method of basic driver commands (click, send keys, get attribute)
    public void click(MobileElement e){
        waitForVisibility(e);
        e.click();
    }

    public void sendKeys(MobileElement e,String txt){
        waitForVisibility(e);
        e.sendKeys(txt);
    }

    public String getAttribute(MobileElement e,String attribute){
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }
    // method to close the app
    public void closeApp(){
        driver.closeApp();
    }
    // method to launch the app
    public void launchApp(){
        driver.launchApp();
    }

    @AfterTest
    public void afterTest() {
        //driver.quit();
    }

}
