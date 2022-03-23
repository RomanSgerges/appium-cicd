package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.time.Duration;

public class RegisterPage extends BaseTest {
    // select the elements
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etPhoneOrEmail")
    private MobileElement emailTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etFullName")
    private MobileElement fullNameTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etUserName")
    private MobileElement userNameTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etUserPhone")
    private MobileElement phoneTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etPassword")
    private MobileElement passwordTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etRePassword")
    private MobileElement rePasswordTxtFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/etPicker")
    private MobileElement birthdayFld;
    @AndroidFindBy(accessibility = "15 February 2022")
    private MobileElement birthdayDateIndex;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement birthdayOkButton;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/rbMale")
    private MobileElement maleFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/rbFemale")
    private MobileElement femaleFld;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.TextView")
    private MobileElement termsAndCondFld;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnSignUp")
    private MobileElement secondSignUpBtn;
    @AndroidFindBy(id = "//android.widget.Toast[@text='Added to cart']")
    private MobileElement errTxt;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/snackbar_text")
    private MobileElement requiredFieldsMessage;



    // take an actions methods

    public RegisterPage enterEmail(String email) {
        sendKeys(emailTxtFld, email);
        return this;
    }
    // end of enter username method
    public RegisterPage enterFullName(String fullname) {
        sendKeys(fullNameTxtFld, fullname);
        return this;
    }
    // end of enter password method

    // end of enter username method
    public RegisterPage enterusername(String username) {
        sendKeys(userNameTxtFld, username);
        return this;
    }
    // end of enter password method
    // end of enter username method
    public RegisterPage enterPhone(String phone) {
        sendKeys(phoneTxtFld, phone);
        return this;
    }
    // end of enter password method
    public RegisterPage enterPassword(String password) {
        sendKeys(passwordTxtFld, password);
        return this;
    }
    // end of enter method

    // end of enter password method
    public RegisterPage enterRePassword(String rePassword) {
        sendKeys(rePasswordTxtFld, rePassword);
        return this;
    }
    // end of enter method

    // end of enter password method
    public RegisterPage enterBirthday() {
        click(birthdayFld);
        click(birthdayDateIndex);
        click(birthdayOkButton);
        return this;
    }

    // end of enter method
    public RegisterPage checkGenderM() {
        click(maleFld);
        return this;
    }
    public RegisterPage checkGenderF() {
        click(femaleFld);
        return this;
    }
    // end of enter method

    public RegisterPage checkTerms() {
        click(termsAndCondFld);
        return this;
    }
    // end of enter method

    // swap
    public void swapRegDown(){
        (new TouchAction(driver))
                .press(PointOption.point(656, 1470))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(PointOption.point(673, 676))
                .release()
                .perform();
    }
    public void swapRegUp(){
        (new TouchAction(driver))
                .press(PointOption.point(714, 511))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                .moveTo(PointOption.point(625, 1373))
                .release()
                .perform();
    }


    public String getErrTxt() {
      return getAttribute(errTxt,"text");
    }
    // end of getErrTxt method
    public String getRequiredTxt() {
        return getAttribute(requiredFieldsMessage,"text");
    }

    // end of  pressSignUp method
}