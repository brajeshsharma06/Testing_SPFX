package Bizportals.TestCases.QuickLinks;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Bizportals.PageObject.LandingPage;
import Bizportals.BaseTest.BaseTest;
import Bizportals.PageObject.LoginPageMicrosoft;
import Bizportals.PageObject.WebPartConfigurationPage;

public class QuickLinksTestCases extends BaseTest {

// This is the example of how to group any test cases from different files and
// then we can run that particular group test cases from xml file
//	@Test(groups = { "Regression" })
//	public void addwebpart() throws InterruptedException {
//		LoginPageMicrosoft loginpage = goToApplication();
//		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
//		WebPartConfigurationPage webPartConfiguration = landingPage.addWebPart("quick links");
//	}

	@Test(dataProvider = "getData")
	public void addwebpart2(String username, String password) throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login(username, password);
		WebPartConfigurationPage webPartConfiguration = landingPage.addWebPart("quick links");
	}

}
// when we are using parameters we should run the test from XML only