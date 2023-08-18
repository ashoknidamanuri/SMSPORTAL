package com.qz.smsportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.smsportal.constants.AppConstants;
import com.qa.smsportal.utils.ElementUtil;



public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	//private locators
	private By Username= By.name("username");
	private By Password= By.id("password-field");
	private By Login=By.xpath("//button[@value='Login']");
	private By forgotpasswordLink = By.linkText("Forgot Password ?");
	//private By registerLink = By.linkText("Create an Account");
	//Page constructor 	
	// 2. page const...
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions/methods:
	//@Step("....getting the login page title.....")
	public String getLoginPageTitle() {
		String title =eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title is :" +  " "+ title);
		return title;
	}
	//@Step("....getting the login page url.....")
	public String getLoginPageurl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
		System.out.println("Login page url is :" +  " "+ url);
		return url;
	}

	public boolean isForgotPwdLinkExists() {
		return eleUtil.waitForElementVisible(forgotpasswordLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();

	}
	//@Step("login with username : {0} and password: {1}")
	public DashbaordPage doLogin(String un, String pw) {
		System.out.println("My App Credentials are" + " " +un + ":"+ pw);
		eleUtil.waitForElementVisible(Username, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		eleUtil.doSendKeys(Password, pw);
		eleUtil.doClick(Login);
		return new DashbaordPage(driver);
	}

}
