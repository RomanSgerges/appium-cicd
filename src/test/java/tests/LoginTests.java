package tests;

import com.qa.BaseTest;
import com.qa.pages.LandingPage;
import com.qa.pages.LoginPage;
import com.qa.pages.RegisterPage;
import com.qa.utils.TestUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
	LandingPage landingPage;
	LoginPage loginPage;
	RegisterPage registerPage;
	InputStream datais;
	JSONObject loginUsers;
	TestUtils utils = new TestUtils();




	
	  @BeforeClass
	  // extract data from JSON file loginUsers.Json to read the test data
	  public void beforeClass() throws IOException {
		  try{
			  // get the data from the json file
			  String dataFileName = "data/loginUsers.json";
			  datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			  JSONTokener tokener = new JSONTokener(datais);
			  loginUsers = new JSONObject(tokener);
		  }catch(Exception e){
			  e.printStackTrace();
		  }finally{
			  if(datais != null){
				  datais.close();
			  }
		  }

	  }

	  @AfterClass
	  public void afterClass() {
	  }
	  
	  @BeforeMethod
	  public void beforeMethod(Method m) {
		  System.out.println("login test before method");
		  landingPage = new LandingPage();
		  loginPage = new LoginPage();
		  registerPage = new RegisterPage();
		  utils.log().info("\n"+"******  starting test :"+m.getName()+"  ********"+"\n");

	  }

	  @AfterMethod
	  public void afterMethod() {
		  System.out.println("login test after method");
	  }


	@Test
	public void emptyInputsLogin() {
		landingPage.clickFirstBtn();
		loginPage.pressSecondLoginBtn();

		String actualErrTxt = loginPage.getRequiredTxt();
		String expectedErrTxt= strings.get("required_fields");
		System.out.println("actual err txt -: "+actualErrTxt+"\n"+"expected err txt -: "+expectedErrTxt);
		Assert.assertEquals(actualErrTxt,expectedErrTxt);


	}


	@Test
	public void successLogin() {
		// add sort assert
		SoftAssert sa = new SoftAssert();
		String buttonName= loginPage.getButtonName();
		sa.assertEquals(buttonName,strings.get("button_name"));
		sa.assertAll();

		// add element actions
		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		loginPage.pressSecondLoginBtn();


	/*	String actualErrTxt = loginPage.getRequiredTxt();
		String expectedErrTxt= strings.get("required_fields");
		System.out.println("actual err txt -: "+actualErrTxt+"\n"+"expected err txt -: "+expectedErrTxt);
		Assert.assertEquals(actualErrTxt,expectedErrTxt);
		System.out.println("There is No error message");*/

	}



}