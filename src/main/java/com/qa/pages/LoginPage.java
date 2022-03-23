package com.qa.pages;
import com.qa.BaseTest;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class LoginPage extends BaseTest {

    // add utils
    TestUtils utils = new TestUtils();
    // select the elements
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etPhoneOrEmail")
    private MobileElement userNameTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etPassword")
    private MobileElement passwordTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnSignIn")
    private MobileElement secondLoginBtn;
    @AndroidFindBy(xpath = "//android.widget.Toast")
    private MobileElement errTxt;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/snackbar_text")
    private MobileElement requiredFieldTxt;

    // home page
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/ivFollowing")
    private MobileElement followButton;



    // take an actions methods
    public LoginPage enterUserName(String username) {
        utils.log().info("login with " + username);
        sendKeys(userNameTxtFld, username);
        return this;
    }
    //start of enter password method
    public LoginPage enterPassword(String password) {
        utils.log().info("password is " + password);
        sendKeys(passwordTxtFld, password);
        return this;
    }
    public void pressSecondLoginBtn() {
        utils.log().info("press second button");
        click(secondLoginBtn);
    }

    public String getErrTxt() {
      return getAttribute(errTxt,"text");
    }
    // end of getErrTxt method

    public String getButtonName(){ return getAttribute(secondLoginBtn,"text");}
    public String getRequiredTxt() {
        String error = getAttribute(requiredFieldTxt,"text");
        utils.log().info("error text is - " + error);
        return error;
    }

    // end of getErrTxt method

    public void swap1() {
        for (int i=0; i<3; i++){
            (new TouchAction(driver))
                    .press(PointOption.point(652, 1656))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                    .moveTo(PointOption.point(728, 970))
                    .release()
                    .perform();
        }
    }

}