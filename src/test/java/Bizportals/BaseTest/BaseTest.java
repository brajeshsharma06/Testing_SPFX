package Bizportals.BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import Bizportals.PageObject.LoginPageMicrosoft;

public class BaseTest {
	WebDriver driver;

	public void intializeDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public LoginPageMicrosoft goToApplication() {
		driver.get("https://m365x23949637.sharepoint.com/sites/Brajesh-CommunicationSite");
		LoginPageMicrosoft loginpage = new LoginPageMicrosoft(driver);
		return loginpage;
	}

	@BeforeMethod
	public void launchApplication() {
		intializeDriver();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][2];

		// 1st Credentials
		data[0][0] = "admin@m365x23949637.onmicrosoft.com";
		data[0][1] = "Batch@123";

//		// 2nd Credentials
//		data[1][0] = "bsharma@m365x23949637.onmicrosoft.com";
//		data[1][1] = "Batch@123";
//
//		// 3rd Credentials
//		data[2][0] = "adelev@m365x23949637.onmicrosoft.com";
//		data[2][1] = "Batch@123";

		return data;
	}
//	@AfterMethod
//	public void tearDown() {
//		driver.close();
//	}
}
