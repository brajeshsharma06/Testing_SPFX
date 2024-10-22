package Bizportals.TestCases.BizportalsSpotlight;

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

public class WebpartCRUD extends BaseTest {
	// Add Web Part
	// Edit Web Part
	// Copy Web Part
	// Add instance web part
	// Delete Web Part
	// Add different web part (Two different web parts)

	@Test
	public void testCrudFunctionality() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		landingPage.addWebPart("Bizportals Spotlight");
		String placeholderText = landingPage.webPartAddMessage();
		AssertJUnit.assertEquals(placeholderText, "Configure BizPortals Spotlight");
		landingPage.deleteWebPart();
		AssertJUnit.assertNotSame(placeholderText, "Configure BizPortals Spotlight");
	}
}
