package Bizportals.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import Bizportals.BaseTest.BaseTest;
import Bizportals.PageObject.LandingPage;
import Bizportals.PageObject.LoginPageMicrosoft;

public class TestLoginFunctionality extends BaseTest {
	@Test
	public void TestLoginWithValidCredentials() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		loginpage.loginPage("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		String successMessage = loginpage.successTextMessage();
		AssertJUnit.assertEquals(successMessage, "Do this to reduce the number of times you are asked to sign in.");
	}

	@Test
	public void TestLoginWithInvalidPassword() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		loginpage.loginPage("admin@m365x23949637.onmicrosoft.com", "Invalid Password");
		String errorLoginMessage = loginpage.errorMessage();
		AssertJUnit.assertEquals(errorLoginMessage,
				"Your account or password is incorrect. If you don't remember your password, reset it now.");
	}

	@Test
	public void TestLoginWithInvalidUsername() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		loginpage.loginPage("invalidusername@M365x72141392.onmicrosoft.com", "Batch@123");
		String incorrectUsername = loginpage.usernameerrorMessage();
		AssertJUnit.assertEquals(incorrectUsername,
				"This username may be incorrect. Make sure that you typed it correctly. Otherwise, contact your admin.");
	}

	@Test
	public void TestLoginWithBlankFields() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		loginpage.loginPage("", "");
		String incorrectUsername = loginpage.usernameerrorMessage();
		AssertJUnit.assertEquals(incorrectUsername, "Enter a valid email address, phone number or Skype name.");
	}

	@Test
	public void Logout() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		landingPage.logout();
	}
}
