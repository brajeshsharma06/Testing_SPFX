package Bizportals.TestCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import Bizportals.BaseTest.BaseTest;
import Bizportals.PageObject.LandingPage;
import Bizportals.PageObject.LoginPageMicrosoft;
import Bizportals.PageObject.WebPartConfigurationPage;

public class TestWebPartConfiguration extends BaseTest {

	@Test
	public void TestWebPartDataPropertiesConfiguration() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		WebPartConfigurationPage webPartConfiguration = landingPage.addWebPart("bizportals headliner");
		webPartConfiguration.webPartConfig("Test List 7", "This is a sample description", "Automated Alert Type",
				"Message", "Modified Date", "Ascending order");

	}
}