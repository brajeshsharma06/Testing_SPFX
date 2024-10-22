package Bizportals.TestCases.WebPartsTestCases;
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

public class TestBizportalsHeadliner extends BaseTest {
// Add item
// Edit item
// Delete item
// Add new object model class for bizportals headliner

	@Test
	public void createNewEntry() throws InterruptedException {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		landingPage.creatingNewEntry("Notice", "New Title", "Hello this is a first automated entry", "Site Owners",
				"Brajesh Sharma");
		String successMessage = landingPage.successMessages();
		AssertJUnit.assertEquals(successMessage, "New item added");
	}

	@Test(dependsOnMethods = "createNewEntry")
	public void editEntryViewForm() {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		landingPage.opensettingGrid();
		landingPage.editFromViewForm("New message from edit view form");
	}

	@Test(dependsOnMethods = "createNewEntry")
	public void editEntry() {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		landingPage.opensettingGrid();
		landingPage.editEntry("The title has been changed");
		String successMessage = landingPage.successMessages();
		AssertJUnit.assertEquals(successMessage, "Item updated");
	}

	@Test(dependsOnMethods = "editEntry")
	public void deleteEntry() {
		LoginPageMicrosoft loginpage = goToApplication();
		LandingPage landingPage = loginpage.login("admin@m365x23949637.onmicrosoft.com", "Batch@123");
		landingPage.opensettingGrid();
		landingPage.deleteItem();
		String successMessage = landingPage.successMessages();
		AssertJUnit.assertEquals(successMessage, "Deleted");
	}
}
