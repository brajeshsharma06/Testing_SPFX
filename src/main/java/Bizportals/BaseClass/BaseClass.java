package Bizportals.BaseClass;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

	@FindBy(xpath = "(//div[@class='ms-TextField-wrapper'])[3]")
	WebElement expiresOn;

	@FindBy(xpath = "//span[text()='2024']")
	WebElement yearPicker;

	@FindBy(xpath = "//button[text()='2024']")
	WebElement selectYear;

	@FindBy(xpath = "//button[text()='May']")
	WebElement selectMonth;

	@FindBy(xpath = "//span[text()='15']")
	WebElement selectDate;

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForWebElementToClickable(WebElement findBy) {
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

	public void waitForWebElementToDisappear(WebElement findBy) {
		wait.until(ExpectedConditions.invisibilityOf(findBy));

	}

	public void selectDate() {
		Actions actions = new Actions(driver);
		actions.moveToElement(expiresOn);
		waitForWebElementToAppear(expiresOn);
		expiresOn.click();
		yearPicker.click();
		waitForWebElementToAppear(selectYear);
		selectYear.click();
		selectMonth.click();
		selectDate.click();
	}
	
	public void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
