package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LandingPage extends BaseTest {
    // select the elements
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnLogin")
    private MobileElement firstLogBtn;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnSignUp")
    private MobileElement firstSignUPBtn;

    // sign up with mail and social media elements
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnSignUp")
    private MobileElement signUPWithEmailBtn;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnFacebookLogin")
    private MobileElement signUPWithFbBtn;
    @AndroidFindBy(id = "bee.bee.hoshaapp:id/btnGoogleLogin")
    private MobileElement signUPWithGoogleBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View[1]/android.widget.Button")
    private MobileElement continueFBookBtn;
    @AndroidFindBy(id = "com.google.android.gms:id/account_name")
    private MobileElement continueGoogBtn;


    public LoginPage clickFirstBtn() {
        click(firstLogBtn);
        return new LoginPage();
    }
    public RegisterPage clickFirstSignUpBtn() {
        click(firstSignUPBtn);
        return new RegisterPage();
    }

    // sign up page and social media login
    public RegisterPage clickWithEmailBtn() {
        click(signUPWithEmailBtn);
        return new RegisterPage();
    }
    public RegisterPage clickWithFacebookBtn() {
        click(signUPWithFbBtn);
        waitForVisibility(continueFBookBtn);
        click(continueFBookBtn);
        return new RegisterPage();
    }
    public RegisterPage clickWithGoogleBtn() {
        click(signUPWithGoogleBtn);
        waitForVisibility(continueGoogBtn);
        click(continueGoogBtn);
        return new RegisterPage();
    }

}