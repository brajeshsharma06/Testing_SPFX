package Bizportals.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Bizportals.BaseClass.BaseClass;

public class LoginPageMicrosoft extends BaseClass {
	WebDriver driver;

	public LoginPageMicrosoft(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "i0116")
	WebElement loginUserName;

	@FindBy(id = "idSIButton9")
	WebElement nextButton;

	@FindBy(xpath = "//input[@name='passwd']")
	WebElement enterPassword;

	@FindBy(id = "idBtn_Back")
	WebElement noButton;

	@FindBy(id = "KmsiDescription")
	WebElement successLoginMessage;

	@FindBy(css = "[id='passwordError']")
	WebElement loginErrorMessage;
	
	
	@FindBy(css = "[id='usernameError']")
	WebElement usernameloginErrorMessage;
	
	public LandingPage login(String username, String password) {
		waitForWebElementToAppear(loginUserName);
		loginUserName.sendKeys(username);
		nextButton.click();
		waitForWebElementToAppear(enterPassword);
		enterPassword.sendKeys(password);
		nextButton.click();
		waitForWebElementToAppear(noButton);
		noButton.click();
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
	}

	public void loginPage(String username, String password) {
		waitForWebElementToAppear(loginUserName);
		loginUserName.sendKeys(username);
		nextButton.click();
		waitForWebElementToAppear(enterPassword);
		enterPassword.sendKeys(password);
		nextButton.click();
	}

	public String successTextMessage() {
		waitForWebElementToAppear(successLoginMessage);
		String successText = successLoginMessage.getText();
		return successText;

	}

	public String errorMessage() {
		waitForWebElementToAppear(loginErrorMessage);
		String errorLoginMessage = loginErrorMessage.getText();
		return errorLoginMessage;
	}
	
	public String usernameerrorMessage() {
		waitForWebElementToAppear(usernameloginErrorMessage);
		String incorrectUsername = usernameloginErrorMessage.getText();
		return incorrectUsername;
	}

}
